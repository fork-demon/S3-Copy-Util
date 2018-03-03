package com.sap.datacloud.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class S3Configuration {
	@Value("${amazons3.source.accessKey}")
	private String sourceAccessKey;
	@Value("${amazons3.destination.accessKey}")
	private String destinationAccessKey;
	@Value("${amazons3.source.secretKey}")
	private String sourceSecretKey;
	@Value("${amazons3.destination.secretKey}")
	private String destinationSecretKey;
	@Value("${amazons3.source.bucketLocation}")
	private String sourceBucketLocation;
	@Value("${amazons3.destination.bucketLocation}")
	private String destinationBucketLocation;
	@Value("${amazons3.source.filePath}")
	private String sourcePath;
	@Value("${amazons3.destination.filePath}")
	private String destinationPath;

	public String getSourceAccessKey() {
		return sourceAccessKey;
	}

	public void setSourceAccessKey(String sourceAccessKey) {
		this.sourceAccessKey = sourceAccessKey;
	}

	public String getDestinationAccessKey() {
		return destinationAccessKey;
	}

	public void setDestinationAccessKey(String destinationAccessKey) {
		this.destinationAccessKey = destinationAccessKey;
	}

	public String getSourceSecretKey() {
		return sourceSecretKey;
	}

	public void setSourceSecretKey(String sourceSecretKey) {
		this.sourceSecretKey = sourceSecretKey;
	}

	public String getDestinationSecretKey() {
		return destinationSecretKey;
	}

	public void setDestinationSecretKey(String destinationSecretKey) {
		this.destinationSecretKey = destinationSecretKey;
	}

	public String getSourceBucketLocation() {
		return sourceBucketLocation;
	}

	public void setSourceBucketLocation(String sourceBucketLocation) {
		this.sourceBucketLocation = sourceBucketLocation;
	}

	public String getDestinationBucketLocation() {
		return destinationBucketLocation;
	}

	public void setDestinationBucketLocation(String destinationBucketLocation) {
		this.destinationBucketLocation = destinationBucketLocation;
	}

	public String getSourcePath() {
		return sourcePath;
	}

	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}

	public String getDestinationPath() {
		return destinationPath;
	}

	public void setDestinationPath(String destinationPath) {
		this.destinationPath = destinationPath;
	}

}
