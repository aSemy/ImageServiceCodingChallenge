package com.bijenkorf.ImageService.service;

import java.awt.Image;
import java.net.URL;

import com.bijenkorf.ImageService.model.DefinedImageType;

public interface CloudHostingService {

	public boolean addImage(final DefinedImageType definedImageType, final String relativeFileLocation, Image processedImage);
	
	public URL getImageURL(final DefinedImageType definedImageType, final String relativeFileLocation);

	public boolean removeImage(final String definedImageType, final String relativeFileLocation);
	public boolean removeImage(final DefinedImageType definedImageType, final String relativeFileLocation);
	
	public boolean imageExists(final DefinedImageType definedImageType, final String relativeFileLocation);
	
	public String createStorageLocation(final DefinedImageType definedImageType, final String relativeFileLocation);
}
