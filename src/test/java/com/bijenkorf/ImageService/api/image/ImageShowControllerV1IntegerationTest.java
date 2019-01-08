package com.bijenkorf.ImageService.api.image;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.net.URL;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.HtmlUtils;

import com.bijenkorf.ImageService.BkImageServiceApplication;
import com.bijenkorf.ImageService.model.DefinedImageType;
import com.bijenkorf.ImageService.properties.ImageServiceProperties;
import com.bijenkorf.ImageService.service.cloud.CloudHostingService;
import com.bijenkorf.ImageService.service.image.ImageFetcherService;
import com.bijenkorf.ImageService.service.image.ImageStorageService;

import org.mockito.*;

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
	@MockBean
	private CloudHostingService cloudService;
	@MockBean
	private ImageFetcherService imageFetcherService;
	@Autowired
	private ImageServiceProperties imageServiceProps;

	/**
	 * <code>/api/v1/image/show/{definedImageType}/{dummySeoName}/?reference={relativeFileLocation}</code
	 * 
	 * @throws Exception
	 */
	@Test
	public void testErrorWhenFileDoesNotExist() throws Exception {

		mvc.perform(get("/api/v1/image/show/" + DefinedImageType.THUMBNAIL.name() + "/dummySeoName/").param("reference",
				HtmlUtils.htmlEscape("/test.jpg")))
				// this data doesn't exist, so expect error
				.andExpect(status().is4xxClientError());
	}

	/**
	 * Test what happens when the original exists and the processed image exists
	 * 
	 * @throws Exception
	 */
	@Test
	public void testHappyFlow_originalHosted_processedHosted() throws Exception {

		final String relativeImageLocation = "/test.jpg";
		DefinedImageType target = DefinedImageType.THUMBNAIL;

		// assumptions for the test
		when(cloudService.imageExists(DefinedImageType.ORIGINAL, relativeImageLocation)).thenReturn(true);
		when(cloudService.imageExists(target, relativeImageLocation)).thenReturn(true);

		// mock cloud service
		URL url = new URL("http://testisgoingprettygoodeh.website/test.jpg");
		when(cloudService.getImageURL(target, relativeImageLocation)).thenReturn(url);

		mvc.perform(get("/api/v1/image/show/" + target.name() + "/dummySeoName/").param("reference",
				HtmlUtils.htmlEscape(relativeImageLocation))).andExpect(status().isOk());

		verify(cloudService, times(1)).imageExists(target, relativeImageLocation);
		verify(cloudService, times(1)).getImageURL(target, relativeImageLocation);
		verifyNoMoreInteractions(cloudService, imageFetcherService);
	}

	/**
	 * Test what happens when the original doesn't exist and the processed image
	 * exists
	 * 
	 * @throws Exception
	 */
	@Test
	public void testHappyFlow_originalNotHosted_processedHosted() throws Exception {

		final String relativeImageLocation = "/test.jpg";
		DefinedImageType target = DefinedImageType.THUMBNAIL;

		// assumptions for the test
		when(cloudService.imageExists(DefinedImageType.ORIGINAL, relativeImageLocation)).thenReturn(false);
		when(cloudService.imageExists(target, relativeImageLocation)).thenReturn(true);

		// mock cloud service
		URL url = new URL("http://testisgoingprettygoodeh.website/test.jpg");
		when(cloudService.getImageURL(target, relativeImageLocation)).thenReturn(url);

		mvc.perform(get("/api/v1/image/show/" + target.name() + "/dummySeoName/").param("reference",
				HtmlUtils.htmlEscape(relativeImageLocation))).andExpect(status().isOk());

		verify(cloudService, times(1)).imageExists(target, relativeImageLocation);
		verify(cloudService, times(1)).getImageURL(target, relativeImageLocation);
		verifyNoMoreInteractions(cloudService, imageFetcherService);
	}

	@Test
	public void testHappyFlow_originalHosted_processedNotHosted() throws Exception {

		final String relativeImageLocation = "/test.jpg";
		DefinedImageType target = DefinedImageType.THUMBNAIL;

		// assumptions for the test
		when(cloudService.imageExists(DefinedImageType.ORIGINAL, relativeImageLocation)).thenReturn(true);
		when(cloudService.imageExists(target, relativeImageLocation)).thenReturn(false).thenReturn(true);

		// mock cloud service
		StringBuilder sb = new StringBuilder();
		sb.append(imageServiceProps.getSourceRootUrl());
		sb.append(relativeImageLocation);
		URL originalURL = new URL(sb.toString());
		when(cloudService.getImageURL(DefinedImageType.ORIGINAL, relativeImageLocation)).thenReturn(originalURL);
		// mock original image
		BufferedImage original = createImage(Color.red, 150, 150);
		when(imageFetcherService.fetchImage(new URL(sb.toString()))).thenReturn(original);
		// mock hosted image
		final URL processedImageUrl = new URL("https://cloudhost.website/img.jpg");
		when(cloudService.getImageURL(target, relativeImageLocation)).thenReturn(processedImageUrl);

		// hit the API
		mvc.perform(get("/api/v1/image/show/" + target.name() + "/dummySeoName/").param("reference",
				HtmlUtils.htmlEscape(relativeImageLocation)))
				// expect status to be 200
				.andExpect(status().isOk())
				// and body to match URL
				.andExpect(content().string(processedImageUrl.toString()));

		verify(cloudService, times(1)).imageExists(target, relativeImageLocation);
		verify(cloudService, times(1)).imageExists(DefinedImageType.ORIGINAL, relativeImageLocation);
		verify(cloudService, times(1)).getImageURL(target, relativeImageLocation);
		verify(cloudService, times(1)).getImageURL(DefinedImageType.ORIGINAL, relativeImageLocation);
		verify(cloudService, times(1)).addImage(eq(target), matches(relativeImageLocation), any(BufferedImage.class));

		verify(imageFetcherService, times(1)).fetchImage(originalURL);

		verifyNoMoreInteractions(cloudService, imageFetcherService);
	}

	@Test
	public void testHappyFlow_originalNotHosted_processedNotHosted() throws Exception {

		final String relativeImageLocation = "/test.jpg";
		DefinedImageType target = DefinedImageType.THUMBNAIL;

		// assumptions for the test
		when(cloudService.imageExists(DefinedImageType.ORIGINAL, relativeImageLocation)).thenReturn(false)
				.thenReturn(true);
		when(cloudService.imageExists(target, relativeImageLocation)).thenReturn(false).thenReturn(true);

		// mock cloud service
		StringBuilder sb = new StringBuilder();
		sb.append(imageServiceProps.getSourceRootUrl());
		sb.append(relativeImageLocation);
		URL originalURL = new URL(sb.toString());
		URL originalURLCloud = new URL("https://cloudhost.website/original.png");
		when(cloudService.getImageURL(DefinedImageType.ORIGINAL, relativeImageLocation)).thenReturn(originalURL);
		// mock original image
		BufferedImage original = createImage(Color.red, 150, 150);
		when(imageFetcherService.fetchImage(new URL(sb.toString()))).thenReturn(original);
		// mock hosted image
		final URL processedImageUrl = new URL("https://cloudhost.website/img.jpg");
		when(cloudService.getImageURL(target, relativeImageLocation)).thenReturn(processedImageUrl);

		// hit the API
		mvc.perform(get("/api/v1/image/show/" + target.name() + "/dummySeoName/").param("reference",
				HtmlUtils.htmlEscape(relativeImageLocation)))
				// expect status to be 200
				.andExpect(status().isOk())
				// and body to match URL
				.andExpect(content().string(processedImageUrl.toString()));

		verify(cloudService, times(1)).imageExists(target, relativeImageLocation);
		verify(cloudService, times(2)).imageExists(DefinedImageType.ORIGINAL, relativeImageLocation);
		verify(cloudService, times(1)).getImageURL(target, relativeImageLocation);
		verify(cloudService, times(1)).getImageURL(DefinedImageType.ORIGINAL, relativeImageLocation);
		verify(cloudService, times(1)).addImage(Mockito.eq(target), matches(relativeImageLocation),
				any(BufferedImage.class));
		verify(cloudService, times(1)).addImage(eq(DefinedImageType.ORIGINAL), matches(relativeImageLocation),
				eq(original));

		verify(imageFetcherService, times(2)).fetchImage(originalURL);

		verifyNoMoreInteractions(cloudService, imageFetcherService);
	}

	private BufferedImage createImage(Color color, int height, int width) {

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, color.getRGB());
			}
		}
		return image;

	}

}
