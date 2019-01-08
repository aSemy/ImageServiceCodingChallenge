package com.bijenkorf.ImageService.service;

public interface CloudHostingService {

	public boolean AddImage(final String predefinedImageType, final String relativeFileLocation);
	
	public boolean RemoveImage(final String predefinedImageType, final String relativeFileLocation);
	
	public boolean ImageExists(final String predefinedImageType, final String relativeFileLocation);
	
	public String createStorageLocation(final String predefinedImageType, final String relativeFileLocation);
}
