package com.bijenkorf.ImageService.model;

import com.bijenkorf.ImageService.processor.image.ImageProcessor;
import com.bijenkorf.ImageService.processor.image.*;

/**
 * Scale type (enumerator), how is the image resized when the new height and
 * width are not in the same aspect ratio as the original image
 *
 */
public enum ImageScaleType {

	/**
	 * Cut of parts of the image that no longer fit the new aspect ratio
	 */
	CROP(new CropImageProcessor()),
	/**
	 * Fill up the parts of the image that no longer fit the new aspect ration with
	 * a background-color specified by the Fill-color property
	 */
	FILL(new FillImageProcessor()),
	/**
	 * Simply squeeze the image to fit the new height and width, this will make the
	 * image look bad in most cases
	 */
	SKEW(new SkewImageProcessor());

	private final ImageProcessor processor;

	private ImageScaleType(final ImageProcessor processor) {
		this.processor = processor;
	}

	public ImageProcessor getProcessor() {
		return processor;
	}

}
