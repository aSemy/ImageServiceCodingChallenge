package com.bijenkorf.ImageService.service;

import java.util.Optional;

public interface ImageStorageService {
	public String storeImage(String predefinedImageType, Optional<String> dummySeoNameOptional,
			String relativeFileLocation_HTMLEscaped);
}
