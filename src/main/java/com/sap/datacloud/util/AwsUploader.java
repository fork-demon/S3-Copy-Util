package com.sap.datacloud.util;

import java.io.InputStream;

import org.apache.camel.Exchange;
import org.apache.camel.component.aws.s3.S3Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;

@Component("aswUploader")
public class AwsUploader {

	@Autowired
	S3Configuration s3Configuration;

	public void uploadToS3(Exchange exchange) {

		try {

			InputStream is = exchange.getIn().getBody(InputStream.class);

			String fileName = (String) exchange.getIn().getHeader("CamelFileName");
			long fileSize = (long) exchange.getIn().getHeader("CamelAwsS3ContentLength");

			String key = (String) exchange.getIn().getHeader(S3Constants.KEY);

			ObjectMetadata meta = new ObjectMetadata();

			meta.setContentLength(fileSize);

			PutObjectRequest request = new PutObjectRequest(s3Configuration.getDestinationBucketLocation(), key, is,
					meta);

			AWSCredentials credentials = new BasicAWSCredentials(s3Configuration.getDestinationAccessKey(),
					s3Configuration.getDestinationSecretKey());

			TransferManager s3transferManager = new TransferManager(credentials);
			System.out.println(" uploading file ..."+ fileName);
			Upload upload = s3transferManager.upload(request);

			try {

				upload.waitForCompletion();

				System.out.println("uploaded file ..."+ fileName);
				s3transferManager.shutdownNow();
			} catch (AmazonClientException | InterruptedException ae) {

				ae.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
