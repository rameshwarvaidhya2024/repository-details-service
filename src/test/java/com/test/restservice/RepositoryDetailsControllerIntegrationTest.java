package com.test.restservice;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.yaml")
public class RepositoryDetailsControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void givenOwnerAndRepoName_whenGetRepoDetails_thenStatus200() throws Exception {

		mvc.perform(get("/repositories/havinhphu188/hibernateex")
				.header("Authorization", "Basic RGVtb1VzZXI6VGVzdDEyMzQ1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.fullName", is("havinhphu188/hibernateex")))
				.andExpect(jsonPath("$.stars", is(0))).andExpect(jsonPath("$.createdAt", is("2017-08-14")));
	}

	@Test
	public void givenWrongOwnerOrRepoName_whenGetRepoDetails_thenStatus404() throws Exception {

		mvc.perform(get("/repositories/havinhphu189/hibernateex123")
				.header("Authorization", "Basic RGVtb1VzZXI6VGVzdDEyMzQ1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.statusCode", is(404)))
				.andExpect(jsonPath("$.message", is("No Repository available with name: hibernateex123")));
	}
	
	@Test
	public void missingOwnerOrRepoName_whenGetRepoDetails_thenStatus404() throws Exception {
		
		mvc.perform(get("/repositories/havinhphu189")
				.header("Authorization", "Basic RGVtb1VzZXI6VGVzdDEyMzQ1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.statusCode", is(404)))
				.andExpect(jsonPath("$.message", is("Invalid API Endpoint")));
	}

}
