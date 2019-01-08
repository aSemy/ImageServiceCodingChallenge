package com.bijenkorf.ImageService.model;

import java.awt.Color;

public class DefinedImageTypeProperties {
	private final int height;
	private final int width;
	private final int quality;
	private final ImageScaleType scaleType;
	private final Color fillColour;
	private final ImageFileType imageType;
	// to implement later
	// private String sourceName;

	private static final Color TRANSPARENT = new Color(0, 0, 0, 0);

	/**
	 * Default {@link #fillColour} to {@link #TRANSPARENT}
	 */
	public DefinedImageTypeProperties(int height, int width, int quality, ImageScaleType scaleType,
			ImageFileType imageType) {
		this(height, width, quality, scaleType, TRANSPARENT, imageType);
	}

	public DefinedImageTypeProperties(int height, int width, int quality, ImageScaleType scaleType, Color fillColour,
			ImageFileType imageType) {
		super();
		this.height = height;
		this.width = width;
		this.quality = quality;
		this.scaleType = scaleType;
		this.fillColour = fillColour;
		this.imageType = imageType;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public int getQuality() {
		return quality;
	}

	public ImageScaleType getScaleType() {
		return scaleType;
	}

	public Color getFillColour() {
		return fillColour;
	}

	public ImageFileType getImageType() {
		return imageType;
	}

}
