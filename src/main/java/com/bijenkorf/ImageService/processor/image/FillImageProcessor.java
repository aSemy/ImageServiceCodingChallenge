package com.bijenkorf.ImageService.processor.image;

import java.awt.Image;
import java.awt.image.BufferedImage;

import com.bijenkorf.ImageService.model.DefinedImageTypeProperties;

public class FillImageProcessor extends ImageProcessor {

	@Override
	public BufferedImage processImage(BufferedImage originalImage, final DefinedImageTypeProperties props) {
		Image skew = originalImage.getScaledInstance(props.getHeight(), props.getWidth(), Image.SCALE_SMOOTH);

		BufferedImage biSkew = new BufferedImage(props.getHeight(), props.getWidth(), Image.SCALE_SMOOTH);
		biSkew.getGraphics().drawImage(skew, 0, 0, null);

		return biSkew;
	}
}
