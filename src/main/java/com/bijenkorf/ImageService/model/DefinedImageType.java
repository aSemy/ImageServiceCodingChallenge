package com.bijenkorf.ImageService.model;

import java.awt.Color;
import java.util.Optional;

public enum DefinedImageType {

	THUMBNAIL(new DefinedImageTypeProperties(100, 100, 100, ImageScaleType.FILL, Color.WHITE, ImageFileType.JPG)),

	ORIGINAL(null);

	private final Optional<DefinedImageTypeProperties> properties;

	private DefinedImageType(DefinedImageTypeProperties properties) {
		this.properties = Optional.ofNullable(properties);
	}

	public boolean hasProperties() {
		return this.properties != null && this.properties.isPresent();
	}

	public Optional<DefinedImageTypeProperties> getProperties() {
		return properties;
	}
}
