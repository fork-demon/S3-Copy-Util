package com.sap.datacloud.util;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.aws.s3.S3Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class S3CopyRouter extends RouteBuilder {

	
	@Autowired
	private S3Configuration configuration;
	
	@Value("${app.mode}")
	private String mode;
	
	@Value("${app.localPath}")
	private String localPath;
	
	@Override
	public void configure() throws Exception {
		
		String to = "S3ToFile".equals(mode) ? "file://"+localPath : "bean:aswUploader?method=uploadToS3";

		from("aws-s3://" + configuration.getSourceBucketLocation() + "?accessKey=" + configuration.getSourceAccessKey()
				+ "&secretKey=" + configuration.getSourceSecretKey()
				+ "&deleteAfterWrite=false&deleteAfterRead=false&delay=5000&maxMessagesPerPoll=5&prefix=" + configuration.getSourcePath()).

						process(new Processor() {

							@Override
							public void process(Exchange exchange) throws Exception {

								String key = (String) exchange.getIn().getHeader(S3Constants.KEY);
								String fileName = key.substring(key.lastIndexOf("/") + 1);
								System.out.println("In processor, file name ... " + fileName);
								long fileSize = (long) exchange.getIn().getHeader(S3Constants.CONTENT_LENGTH);
								System.out.println("In processor, file length ... " + fileSize);
								InMemoryIdempotentRepo context = InMemoryIdempotentRepo.getContext();
								if (context.getCache().containsKey(key)) {

									System.out.println("duplicate file : "+fileName +" found, stopping the route");
									exchange.setProperty(Exchange.ROUTE_STOP, Boolean.TRUE);
								} else {
									context.getCache().put(key, "PRESENT");
								}
								exchange.getIn().setHeader(S3Constants.KEY,
										configuration.getDestinationPath() + fileName);
								exchange.getIn().setHeader(S3Constants.CONTENT_LENGTH, fileSize);
								exchange.getIn().setHeader("CamelFileName", fileName);

							}
						}).to(to);

	}
	
}
