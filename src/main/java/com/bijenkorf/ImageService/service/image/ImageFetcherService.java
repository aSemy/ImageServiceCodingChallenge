package com.bijenkorf.ImageService.service.image;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public interface ImageFetcherService {
	public BufferedImage fetchImage(URL url) throws IOException;
}
