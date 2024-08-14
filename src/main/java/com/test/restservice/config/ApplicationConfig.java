package com.test.restservice.config;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.logging.Logger;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.test.restservice.dto.RepositoryDetailDTO;
import com.test.restservice.model.RepositoryDetail;

/**
 * The Class ApplicationConfig is responsible for initializing beans managed by
 * spring container
 */
@Configuration
public class ApplicationConfig {

	private static final Logger LOG = Logger.getLogger(ApplicationConfig.class.getName());

	/**
	 * Initialize Model mapper bean with type map to enable RepositoryDetail model
	 * (representing response from git endpoint) to DTO mapping (representing
	 * response to client)
	 *
	 * @return the model mapper
	 */
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();

		Converter<String, String> formatDate = ctx -> LocalDate
				.ofInstant(Instant.parse(ctx.getSource().toString()), ZoneId.of("Asia/Singapore")).toString();

		modelMapper.typeMap(RepositoryDetail.class, RepositoryDetailDTO.class, "repositoryDTOMapping")
				.addMappings(mapper -> {
					mapper.map(source -> source.getFull_name(), RepositoryDetailDTO::setFullName);
					mapper.map(source -> source.getDescription(), RepositoryDetailDTO::setDescription);
					mapper.map(source -> source.getClone_url(), RepositoryDetailDTO::setCloneUrl);
					mapper.map(source -> source.getStargazers_count(), RepositoryDetailDTO::setStars);
					mapper.using(formatDate).map(source -> source.getCreated_at(), RepositoryDetailDTO::setCreatedAt);
				});

		return modelMapper;
	}

	/**
	 * Object mapper.
	 *
	 * @return the object mapper
	 */
	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		return objectMapper;
	}
}
