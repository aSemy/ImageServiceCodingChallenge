package com.bijenkorf.ImageService.api;

import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ApiV1Controller {

	@RequestMapping("/image/show/{predefinedImageType}/{dummySeoName}/?reference={relativeFileLocation}")
	@ResponseBody
	public String imageShowWithSEO(//
			@PathVariable("predefinedImageType") final String predefinedImageType, //
			@PathVariable("dummySeoName") final String dummySeoName, //
			@PathVariable("relativeFileLocation") final String relativeFileLocation) {

		return imageShow(predefinedImageType, Optional.ofNullable(dummySeoName), relativeFileLocation);
	}

	@RequestMapping("/image/show/{predefinedImageType}/?reference={relativeFileLocation}")
	@ResponseBody
	public String imageShowWithoutSEO(//
			@PathVariable("predefinedImageType") final String predefinedImageType, //
			@PathVariable("relativeFileLocation") final String relativeFileLocation) {

		return imageShow(predefinedImageType, Optional.empty(), relativeFileLocation);

	}

	public String imageShow(final String predefinedImageType, final Optional<String> dummySeoNameOptional,
			final String relativeFileLocation) {

		return null;
	}
}
