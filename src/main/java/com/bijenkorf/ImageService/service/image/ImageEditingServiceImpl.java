package com.bijenkorf.ImageService.service.image;

import java.awt.image.BufferedImage;

import org.springframework.stereotype.Service;

import com.bijenkorf.ImageService.model.DefinedImageType;
import com.bijenkorf.ImageService.model.DefinedImageTypeProperties;
import com.bijenkorf.ImageService.processor.image.ImageProcessor;

@Service
public class ImageEditingServiceImpl implements ImageEditingService {

	@Override
	public BufferedImage processImage(final BufferedImage originalImage, final DefinedImageType targetImageType) {

		if (targetImageType.hasProperties()) {
			DefinedImageTypeProperties props = targetImageType.getProperties().get();

			ImageProcessor processor = props.getScaleType().getProcessor();

			return processor.processImage(originalImage, props);

		} else {
			// error, trying to edit image but target image type had no properties
			// TODO throw exception
			return null;
		}
	}
}
