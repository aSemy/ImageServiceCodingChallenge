package com.bijenkorf.ImageService.service;

import org.springframework.stereotype.Service;

@Service
public class AWSHostingService implements CloudHostingService {

	@Override
	public boolean AddImage(String predefinedImageType, String relativeFileLocation) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean RemoveImage(String predefinedImageType, String relativeFileLocation) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ImageExists(String predefinedImageType, String relativeFileLocation) {
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
	public String createStorageLocation(String predefinedImageType, String relativeFileLocation) {
		StringBuilder builder = new StringBuilder();

		final String fileSeparator = "/";
		final String fileSeparatorEscaped = "-";

		// root dir
		builder.append(fileSeparator);
		// folder, e.g. /thumbnail/
		builder.append(predefinedImageType);
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
