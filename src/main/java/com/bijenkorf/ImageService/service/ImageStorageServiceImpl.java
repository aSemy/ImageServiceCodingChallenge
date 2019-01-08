package com.bijenkorf.ImageService.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.bijenkorf.ImageService.properties.ImageServiceProperties;

@Service
public class ImageStorageServiceImpl  implements ImageStorageService {

	@Autowired
	private ImageServiceProperties imageServiceProperties;

	@Override
	public String storeImage(String predefinedImageType, Optional<String> dummySeoNameOptional,
			String relativeFileLocation_HTMLEscaped) {

		final String relativeFileLocation = HtmlUtils.htmlUnescape(relativeFileLocation_HTMLEscaped);

		// fetch image

		final String imageToOptimise = imageServiceProperties.getSourceRootUrl() + relativeFileLocation;

		// get image type

		// ignore seo name for now

		//

		return null;
	}


}
