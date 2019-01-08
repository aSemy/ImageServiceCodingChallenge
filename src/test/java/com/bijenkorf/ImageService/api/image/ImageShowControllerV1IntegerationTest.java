package com.bijenkorf.ImageService.api.image;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.bijenkorf.ImageService.BkImageServiceApplication;
import com.bijenkorf.ImageService.service.image.ImageStorageService;

/**
 * Integration test on API endpoints
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = BkImageServiceApplication.class)
@AutoConfigureMockMvc
public class ImageShowControllerV1IntegerationTest {

	@Autowired
	private MockMvc mvc;
	@Autowired
	private ImageStorageService imageStorageService;

	/**
	 * <code>/api/v1/image/show/{definedImageType}/{dummySeoName}/?reference={relativeFileLocation}</code
	 * 
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {

		mvc.perform(get("/api/v1/image/show/THUMBNAIL/dummySeoName/?reference=/test.jpg"))
				// this data doesn't exist, so expect error
				.andExpect(status().is4xxClientError());
	}

}
