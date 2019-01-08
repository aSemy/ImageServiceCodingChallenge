package com.bijenkorf.ImageService.service.image;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.bijenkorf.ImageService.model.DefinedImageType;
import com.bijenkorf.ImageService.properties.ImageServiceProperties;
import com.bijenkorf.ImageService.service.cloud.CloudHostingService;

@Service
public class ImageStorageServiceImpl implements ImageStorageService {

	@Autowired
	private ImageServiceProperties imageServiceProperties;
	@Autowired
	private CloudHostingService cloudHostingService;
	@Autowired
	private ImageEditingService imageEditingService;
	@Autowired
	private ImageFetcherService imageFetcherService;

	@Override
	public String storeImage(DefinedImageType targetImageType, Optional<String> dummySeoNameOptional,
			String relativeFileLocation_HTMLEscaped) throws MalformedURLException, IOException {
		// ignore seo name for now

		// set up variables

		// convert escaped to normal
		final String relativeFileLocation = HtmlUtils.htmlUnescape(relativeFileLocation_HTMLEscaped);

		////////////////
		// core logic //
		// \/\/\/\/\/\///

		// check if optimised image is hosted
		boolean isOptimisedHosted = cloudHostingService.imageExists(targetImageType, relativeFileLocation);

		if (isOptimisedHosted) {
			// Great! The optimised image is hosted
			// We just have to fetch and return it

			// TODO change return type to URL?
			return cloudHostingService.getImageURL(targetImageType, relativeFileLocation).toString();
		} else {
			// the optimised image isn't hosted, so we need to
			// - host the original (if necessary)
			// - process the original
			// - host the optimised image

			// get the URL of the hosted original
			// (by hosting it if necessary)
			URL originalImageURL = storeOrFetchOriginalImageURL(relativeFileLocation);

			// fetch the original
			BufferedImage originalImage = imageFetcherService.fetchImage(originalImageURL);

			// now process the original so it's optimised
			BufferedImage processedImage = imageEditingService.processImage(originalImage, targetImageType);

			// now store it
			cloudHostingService.addImage(targetImageType, relativeFileLocation, processedImage);

			// finally return it
			return cloudHostingService.getImageURL(targetImageType, relativeFileLocation).toString();
		}
	}

	/**
	 * Get the URL of the original image. Either fetch it from the given URL and
	 * store it, or fetch it from our cloud storage
	 * 
	 * @param relativeFileLocation
	 * @return
	 * @throws IOException
	 */
	private URL storeOrFetchOriginalImageURL(final String relativeFileLocation) throws IOException {

		// check if image is stored in cloud
		boolean isOriginalHosted = cloudHostingService.imageExists(DefinedImageType.ORIGINAL, relativeFileLocation);

		if (!isOriginalHosted) {
			// the original is NOT hosted, so store it

			// fetch it
			BufferedImage originalImage = imageFetcherService.fetchImage(getImageURL(relativeFileLocation));

			// store it
			cloudHostingService.addImage(DefinedImageType.ORIGINAL, relativeFileLocation, originalImage);
			// confirm it now exists
			// TODO throw a better exception
			assert cloudHostingService.imageExists(DefinedImageType.ORIGINAL, relativeFileLocation);
		}

		// the original is now hosted, so return it
		return cloudHostingService.getImageURL(DefinedImageType.ORIGINAL, relativeFileLocation);
	}

	private URL getImageURL(final String relativeFileLocation) throws MalformedURLException {
		StringBuilder sb = new StringBuilder();
		sb.append(imageServiceProperties.getSourceRootUrl());
		sb.append(relativeFileLocation);

		return new URL(sb.toString());
	}
}
