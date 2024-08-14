package com.test.restservice.service;

import java.util.logging.Logger;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.restservice.dto.RepositoryDetailDTO;
import com.test.restservice.exception.NoSuchRepositoryExistsException;
import com.test.restservice.model.RepositoryDetail;

/**
 * The Class RepositoryDetailsService handles business logic by making API call
 * to git repo endpoint and store the result in Redis
 */
@Service
public class RepositoryDetailsService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ObjectMapper objectMapper;

	@Value(value = "${git.api.urlPrefix}")
	private String gitAPIPrefix;

	private static final String REPOSITORY_DETAILS = "repositoryDetails";

	private static final Logger LOG = Logger.getLogger(RepositoryDetailsService.class.getName());

	/**
	 * Gets the repository details.
	 *
	 * @param owner          the owner
	 * @param repositoryName the repository name
	 * @return the repository details from Redis Cache if hit or call Git API to
	 *         fetch details and cache in Redis
	 * @throws NoSuchRepositoryExistsException if the git repos endpoint returns 404
	 *                                         - not found
	 */
	@Cacheable(REPOSITORY_DETAILS)
	public String getRepositoryDetails(String owner, String repositoryName) {

		RestClient restClient = RestClient.create();

		String result = restClient.get().uri(gitAPIPrefix + "/repos/{owner}/{repositoryName}", owner, repositoryName)
				.accept(MediaType.APPLICATION_JSON).exchange((request, response) -> {
					String jsonString = "";
					System.out.println(response.getBody());
					if (response.getStatusCode().is4xxClientError()) {
						throw new NoSuchRepositoryExistsException(
								"No Repository available with name: " + repositoryName);
					} else {
						RepositoryDetailDTO repositoryDetailDTO = modelMapper.map(
								response.bodyTo(RepositoryDetail.class), RepositoryDetailDTO.class,
								"repositoryDTOMapping");
						jsonString = objectMapper.writeValueAsString(repositoryDetailDTO);
					}
					return jsonString;
				});

		LOG.info("From api call: " + result);

		return result;
	}
}
