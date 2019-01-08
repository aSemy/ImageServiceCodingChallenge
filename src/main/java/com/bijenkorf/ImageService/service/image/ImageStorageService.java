package com.bijenkorf.ImageService.service.image;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Optional;

import com.bijenkorf.ImageService.model.DefinedImageType;

public interface ImageStorageService {
	public String storeImage(DefinedImageType targetImageType, Optional<String> dummySeoNameOptional,
			String relativeFileLocation_HTMLEscaped) throws MalformedURLException, IOException;
}
