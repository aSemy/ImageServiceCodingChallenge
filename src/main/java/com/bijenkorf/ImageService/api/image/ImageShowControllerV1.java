package com.bijenkorf.ImageService.api.image;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bijenkorf.ImageService.model.DefinedImageType;
import com.bijenkorf.ImageService.service.ImageStorageService;

@RestController
@RequestMapping("/api/v1/image")
public class ImageShowControllerV1 {

	@Autowired
	private ImageStorageService imageStorageService;

	/**
	 * A full example request url would look like:
	 * <code>~/image/show/thumbnail/dept-blazer/?reference=%2F027%2F790%2F13_0277901000150001_pro_mod_frt_02_1108_1528_1059540.jpg</code>
	 * 
	 * @param definedImageType                 The name of the definition type, for
	 *                                         example thumbnail, or detail-large,
	 *                                         etc. The predefined type holds all
	 *                                         the properties for the correct
	 *                                         resizing of the image, see the
	 *                                         Predefined image types paragraph
	 *                                         below
	 * @param dummySeoName                     (Optional) a non-used parameter that
	 *                                         could be used to trick search engines
	 *                                         into understanding the image a bit
	 *                                         more, for example it could contain
	 *                                         the name of the brand and/or product.
	 * @param relativeFileLocation_HTMLEscaped The unique filename and/or relative
	 *                                         path to identify the original image
	 *                                         on the source domain, for example
	 *                                         <code>"/027/790/13_0277901000150001_pro_mod_frt_02_1108_1528_1059540.jpg"</code>
	 * @return
	 */
	@RequestMapping("/show/{definedImageType}/{dummySeoName}/?reference={relativeFileLocation}")
	@ResponseBody
	public ResponseEntity<String> imageShowWithSEO(//
			@PathVariable("definedImageType") final DefinedImageType definedImageType, //
			@PathVariable("dummySeoName") final String dummySeoName, //
			@PathVariable("relativeFileLocation_HTMLEscaped") final String relativeFileLocation_HTMLEscaped) {

		return imageShow(definedImageType, Optional.ofNullable(dummySeoName), relativeFileLocation_HTMLEscaped);
	}

	/**
	 * A full example request url would look like:
	 * <code>~/image/show/thumbnail/dept-blazer/?reference=%2F027%2F790%2F13_0277901000150001_pro_mod_frt_02_1108_1528_1059540.jpg</code>
	 * 
	 * @param definedImageType                 The name of the definition type, for
	 *                                         example thumbnail, or detail-large,
	 *                                         etc. The predefined type holds all
	 *                                         the properties for the correct
	 *                                         resizing of the image, see the
	 *                                         Predefined image types paragraph
	 *                                         below
	 * @param relativeFileLocation_HTMLEscaped The unique filename and/or relative
	 *                                         path to identify the original image
	 *                                         on the source domain, for example
	 *                                         <code>"/027/790/13_0277901000150001_pro_mod_frt_02_1108_1528_1059540.jpg"</code>
	 * @return
	 */
	@RequestMapping("/show/{definedImageType}/?reference={relativeFileLocation}")
	@ResponseBody
	public ResponseEntity<String> imageShowWithoutSEO(//
			@PathVariable("definedImageType") final DefinedImageType definedImageType, //
			@PathVariable("relativeFileLocation_HTMLEscaped") final String relativeFileLocation_HTMLEscaped) {

		return imageShow(definedImageType, Optional.empty(), relativeFileLocation_HTMLEscaped);

	}

	public ResponseEntity<String> imageShow(final DefinedImageType definedImageType,
			final Optional<String> dummySeoNameOptional, final String relativeFileLocation) {

		try {
			String storedImageURL = imageStorageService.storeImage(definedImageType, dummySeoNameOptional,
					relativeFileLocation);
			return ResponseEntity.ok(storedImageURL);
		} catch (IOException e) {
// TODO log error
			return ResponseEntity.notFound().build();
		}
	}

}
