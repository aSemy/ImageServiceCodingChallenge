package com.bijenkorf.ImageService.service.image;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.awt.image.BufferedImage;

import org.junit.Test;

import com.bijenkorf.ImageService.model.DefinedImageType;

public class ImageEditingServiceImplTest {

	@Test
	public void test() {
		ImageEditingServiceImpl service = new ImageEditingServiceImpl();
		
		DefinedImageType targetDIT = DefinedImageType.THUMBNAIL;
		
		BufferedImage originalImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x< originalImage.getWidth();x++) {
			for (int y = 0; y < originalImage.getHeight(); y++) {
				originalImage.setRGB(x, y, Color.blue.getRGB());
			}
		}
		
		
		BufferedImage processedImage = service.processImage(originalImage, targetDIT);

		assertEquals(targetDIT.getProperties().get().getHeight(), processedImage.getHeight());
		assertEquals(targetDIT.getProperties().get().getWidth(), processedImage.getWidth());
	}
	
}
