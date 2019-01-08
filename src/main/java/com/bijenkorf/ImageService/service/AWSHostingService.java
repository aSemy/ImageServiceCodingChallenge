package com.bijenkorf.ImageService.service;

import java.awt.Image;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.bijenkorf.ImageService.model.DefinedImageType;

@Service
public class AWSHostingService implements CloudHostingService {

	@Override
	public boolean addImage(final DefinedImageType definedImageType, String relativeFileLocation, final Image image) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeImage(String definedImageType, String relativeFileLocation) {
		return removeImage(DefinedImageType.valueOf(definedImageType), relativeFileLocation);
	}

	@Override
	public boolean removeImage(final DefinedImageType definedImageType, String relativeFileLocation) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public URL getImageURL(DefinedImageType definedImageType, String relativeFileLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean imageExists(final DefinedImageType definedImageType, String relativeFileLocation) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * There will be a great volume of images stored to the S3 bucket, especially
	 * after all images have been resized. Having a proper directory structure will
	 * help to maintain the images. The defined strategy is:
	 * <code>~/[predefined-type-name]/[first-4-chars]/[second-4-chars]/[unique-original-image-file-name]</code>
	 * 
	 * @param predefinedImageType
	 * @param relativeFileLocation
	 * @return
	 */
	@Override
	public String createStorageLocation(final DefinedImageType definedImageType, String relativeFileLocation) {
		StringBuilder builder = new StringBuilder();

		final String fileSeparator = "/";
		final String fileSeparatorEscaped = "-";

		// root dir
		builder.append(fileSeparator);
		// folder, e.g. /thumbnail/
		builder.append(definedImageType.name());
		builder.append(fileSeparator);

		// escape file separator
		// e.g. "/projectdir/images/coollogo.jpg" -> "-projectdir-images-coollogo.jpg"
		final String escapedFileLocation = relativeFileLocation.replace(fileSeparator, fileSeparatorEscaped);

		// if there's enough characters, create a folder
		if (escapedFileLocation.length() > 4) {
			builder.append(escapedFileLocation.substring(0, 4));
			builder.append(fileSeparator);

			if (escapedFileLocation.length() > 8) {
				builder.append(escapedFileLocation.substring(4, 8));
				builder.append(fileSeparator);
			}
		}

		builder.append(escapedFileLocation);

		return builder.toString();
	}

}
