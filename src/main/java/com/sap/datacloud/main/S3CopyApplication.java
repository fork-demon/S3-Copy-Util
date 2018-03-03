package com.sap.datacloud.main;

import org.apache.camel.CamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import com.sap.datacloud.util.S3CopyRouter;

@PropertySource("classpath:application.properties")
@ComponentScan("com.sap.datacloud")
@SpringBootApplication
public class S3CopyApplication{
	
	@Autowired
	S3CopyRouter router;

	public static void main(String[] args) {
		SpringApplication.run(S3CopyApplication.class, args);
	}

	
}
