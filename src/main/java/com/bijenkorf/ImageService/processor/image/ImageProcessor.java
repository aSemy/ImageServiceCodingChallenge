package com.bijenkorf.ImageService.processor.image;

import java.awt.image.BufferedImage;

import com.bijenkorf.ImageService.model.DefinedImageTypeProperties;

public abstract class ImageProcessor {

	public abstract BufferedImage processImage(BufferedImage originalImage, final DefinedImageTypeProperties props);
}
