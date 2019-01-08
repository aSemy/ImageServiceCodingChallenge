package com.bijenkorf.ImageService.model;

import java.awt.Color;

public class DefinedImageTypeProperties {
	private long height;
	private long width;
	private long quality;
	private ImageScaleType scaleType;
	private Color fillColour;
	private ImageFileType imageType;

	public DefinedImageTypeProperties(long height, long width, long quality, ImageScaleType scaleType,
			Color fillColour, ImageFileType imageType) {
		super();
		this.height = height;
		this.width = width;
		this.quality = quality;
		this.scaleType = scaleType;
		this.fillColour = fillColour;
		this.imageType = imageType;
	}

	public long getHeight() {
		return height;
	}

	public void setHeight(long height) {
		this.height = height;
	}

	public long getWidth() {
		return width;
	}

	public void setWidth(long width) {
		this.width = width;
	}

	public long getQuality() {
		return quality;
	}

	public void setQuality(long quality) {
		this.quality = quality;
	}

	public ImageScaleType getScaleType() {
		return scaleType;
	}

	public void setScaleType(ImageScaleType scaleType) {
		this.scaleType = scaleType;
	}

	public Color getFillColour() {
		return fillColour;
	}

	public void setFillColour(Color fillColour) {
		this.fillColour = fillColour;
	}

	public void setFillColour(String hex) {
		this.fillColour = Color.decode(hex);
	}

	public ImageFileType getImageType() {
		return imageType;
	}

	public void setImageType(ImageFileType imageType) {
		this.imageType = imageType;
	}

	// to implement later
	// private String sourceName;

}
