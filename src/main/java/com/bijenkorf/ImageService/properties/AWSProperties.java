package com.bijenkorf.ImageService.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="aws-properties")
public class AWSProperties {
	/**
	 * endpoint to the S3 bucket to store the images in.
	 */
	private String awsS3Endpoint;
	/**
	 * the access key for the S3 bucket
	 */
	private String awsAccesskey;
	/**
	 * the secret key for the S3 bucket
	 */
	private String awsSecretkey;

	public String getAwsS3Endpoint() {
		return awsS3Endpoint;
	}

	public void setAwsS3Endpoint(String awsS3Endpoint) {
		this.awsS3Endpoint = awsS3Endpoint;
	}

	public String getAwsAccesskey() {
		return awsAccesskey;
	}

	public void setAwsAccesskey(String awsAccesskey) {
		this.awsAccesskey = awsAccesskey;
	}

	public String getAwsSecretkey() {
		return awsSecretkey;
	}

	public void setAwsSecretkey(String awsSecretkey) {
		this.awsSecretkey = awsSecretkey;
	}
}
