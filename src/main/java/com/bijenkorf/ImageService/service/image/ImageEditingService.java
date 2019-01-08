package com.bijenkorf.ImageService.service.image;

import java.awt.image.BufferedImage;

import com.bijenkorf.ImageService.model.DefinedImageType;

public interface ImageEditingService {

	public BufferedImage processImage(final BufferedImage originalImage , final DefinedImageType targetImageType) ;	
}
