package com.bijenkorf.ImageService.model;

/**
 * Scale type (enumerator), how is the image resized when the new height and
 * width are not in the same aspect ratio as the original image
 *
 */
public enum ImageScaleType {

	/**
	 * Crop, cut of parts of the image that no longer fit the new aspect ratio
	 */
	CROP,
	/**
	 * Fill, fill up the parts of the image that no longer fit the new aspect ration
	 * with a background-color specified by the Fill-color property
	 */
	FILL,
	/**
	 * Skew, simply squeeze the image to fit the new height and width, this will
	 * make the image look bad in most cases
	 */
	SKEW
}
