package com.bijenkorf.ImageService.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "image-service-properties")
public class ImageServiceProperties {
	/**
	 * the root url where to get the images from, i.e.
	 * http://www.debijenkorf.nl/INTERSHOP/static/WFS/dbk-shop-Site/-/dbk-shop/nl_NL/product-images/.
	 */
	private String sourceRootUrl;

	public String getSourceRootUrl() {
		return sourceRootUrl;
	}

	public void setSourceRootUrl(String sourceRootUrl) {
		this.sourceRootUrl = sourceRootUrl;
	}
}
