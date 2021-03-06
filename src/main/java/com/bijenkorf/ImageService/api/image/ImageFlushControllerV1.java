package com.bijenkorf.ImageService.api.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import com.bijenkorf.ImageService.model.DefinedImageType;
import com.bijenkorf.ImageService.service.cloud.CloudHostingService;

@RestController
@RequestMapping("/api/v1/image")
public class ImageFlushControllerV1 {

	@Autowired
	private CloudHostingService cloudHostingService;

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
	 * @param relativeFileLocation_HTMLEscaped
	 * @return
	 */
	@RequestMapping(name = "/flush/{definedImageType}")
	@ResponseBody
	public ResponseEntity<Object> flushImageType(//
			@PathVariable("definedImageType") final DefinedImageType definedImageType, //
			@RequestParam(name = "reference", required = true) final String relativeFileLocation_HTMLEscaped) {

		boolean removeImage = cloudHostingService.removeImage(definedImageType,
				HtmlUtils.htmlUnescape(relativeFileLocation_HTMLEscaped));

		if (removeImage)
			return ResponseEntity.ok().build();
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	@RequestMapping("/flush/original/")
	@ResponseBody
	public ResponseEntity<Object> flushAll(
			@RequestParam(name = "reference", required = true) final String relativeFileLocation_HTMLEscaped) {

		return ResponseEntity.ok().build();
	}
}
