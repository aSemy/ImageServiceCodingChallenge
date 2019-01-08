package com.bijenkorf.ImageService.service;

import java.awt.Image;
import java.awt.image.BufferedImage;

import com.bijenkorf.ImageService.model.DefinedImageType;
import com.bijenkorf.ImageService.model.DefinedImageTypeProperties;

public class ImageEditingServiceImpl implements ImageEditingService {

	@Override
	public Image processImage(final BufferedImage originalImage, final DefinedImageType targetImageType) {

		if (targetImageType.hasProperties()) {
			DefinedImageTypeProperties props = targetImageType.getProperties().get();

			Image scaled = originalImage.getScaledInstance(props.getHeight(), props.getWidth(), Image.SCALE_SMOOTH);

			return scaled;
		} else {
			// error, trying to edit image but target image type had no properties
			// TODO throw exception
			return null;
		}
	}
}
