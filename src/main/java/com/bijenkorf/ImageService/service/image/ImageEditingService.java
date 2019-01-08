package com.bijenkorf.ImageService.service.image;

import java.awt.Image;
import java.awt.image.BufferedImage;

import com.bijenkorf.ImageService.model.DefinedImageType;

public interface ImageEditingService {

	public Image processImage(final BufferedImage originalImage , final DefinedImageType targetImageType) ;
	
}
