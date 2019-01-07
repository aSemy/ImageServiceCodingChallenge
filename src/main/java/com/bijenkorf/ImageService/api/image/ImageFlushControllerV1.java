package com.bijenkorf.ImageService.api.image;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/image")
public class ImageFlushControllerV1 {

	/**
	 * In some cases it might be required to flush the original and optimized images
	 * from the Image service S3 buckets, and get new images. This can be done
	 * manually by removing the images from the AWS S3 bucket, but it should also be
	 * able to do this, per image through an url endpoint.
	 * <p>
	 * <code>~/image/flush/&lt;predefined-image-type&gt;/reference=&lt;unique-original-image-filename&gt;</code>
	 * <p>
	 * This will remove the referenced image from the S3 bucket of the specific
	 * predefined-image-type. If the predefined-image-type is set to "original", all
	 * optimized images will be removed for this reference. For example, to remove
	 * the thumbnail variant from the S3. :
	 * <p>
	 * <code>~/image/flush/thumbnail/?reference=%2F027%2F790%2F13_0277901000150001_pro_mod_frt_02_1108_1528_1059540.jpg</code>
	 * <p>
	 * To remove all available images from the S3:
	 * <p>
	 * <code>~/image/flush/original/?reference=%2F027%2F790%2F13_0277901000150001_pro_mod_frt_02_1108_1528_1059540.jpg</code>
	 * 
	 * @param predefinedImageType
	 * @param relativeFileLocation
	 * @return
	 */
	@RequestMapping("/{predefinedImageType}/?reference={relativeFileLocation}")
	@ResponseBody
	public ResponseEntity<Object> flushImageType(//
			@PathVariable("predefinedImageType") final String predefinedImageType, //
			@PathVariable("relativeFileLocation") final String relativeFileLocation) {

		return ResponseEntity.ok().build();
	}

	@RequestMapping("/original/?reference={relativeFileLocation}")
	@ResponseBody
	public ResponseEntity<Object> flushAll(@PathVariable("relativeFileLocation") final String relativeFileLocation) {

		return ResponseEntity.ok().build();
	}
}
