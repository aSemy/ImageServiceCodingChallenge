package com.bijenkorf.ImageService.api.image;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.bijenkorf.ImageService.model.DefinedImageType;
import com.bijenkorf.ImageService.service.image.ImageStorageService;

/**
 * Integration test on API endpoints
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ImageShowControllerV1.class)
public class ImageShowControllerV1Test {

	@Autowired
	private MockMvc mvc;
	@MockBean
	private ImageStorageService imageStorageService;

	/**
	 * <code>/api/v1/image/show/{definedImageType}/{dummySeoName}/?reference={relativeFileLocation}</code
	 * 
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {
		ResultActions perform = mvc.perform(
				get("/api/v1/image/show/" + DefinedImageType.THUMBNAIL.name() + "/dummySeoName/?reference=/test.jpg"));

		MvcResult andReturn = perform.andReturn();

		MockHttpServletResponse response = andReturn.getResponse();

		assertEquals(404, response.getStatus());
	}

	@Test
	public void testInvalidPredefinedImage() throws Exception {

		// assert we're testing an invalid image def
		String invalidImageType = "asdasdad";
		assert Arrays.stream(DefinedImageType.values()).noneMatch(dit -> dit.name().equalsIgnoreCase(invalidImageType));

		mvc.perform(get("/api/v1/image/show/" + invalidImageType + "/dummySeoName").param("reference", "/test.jpg"))
				.andExpect(status().is4xxClientError());

		// as there's an error there shouldn't be interactions with the service
		verifyNoMoreInteractions(imageStorageService);
	}

	@Test
	public void testValidPredefinedImage() throws Exception {

		ResultActions perform = mvc.perform(
				get("/api/v1/image/show/" + DefinedImageType.THUMBNAIL.name() + "/dummySeoName/?reference=/test.jpg"));

		verifyNoMoreInteractions(imageStorageService);

		MvcResult andReturn = perform.andReturn();

		MockHttpServletResponse response = andReturn.getResponse();

		assertEquals(404, response.getStatus());
	}
}
