package com.bijenkorf.ImageService.service.image;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

@Service
public class ImageFetcherServiceImpl implements ImageFetcherService {

	@Override
	public BufferedImage fetchImage(URL url) throws IOException {
		return ImageIO.read(url);
	}
}
