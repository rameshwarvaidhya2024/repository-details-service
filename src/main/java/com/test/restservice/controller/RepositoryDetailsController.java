package com.test.restservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.restservice.service.RepositoryDetailsService;

/**
 * The Class RepositoryDetailsController handles requests from client related to
 * repositories
 */
@RestController
@RequestMapping("/repositories")
public class RepositoryDetailsController {

	@Autowired
	private RepositoryDetailsService repositoryDetailsService;

	/**
	 * Gets the repository details.
	 *
	 * @param owner          the owner id of repository
	 * @param repositoryName the repository name
	 * @return the repository details in JSON format that contains fullName,
	 *         description, cloneUrl, stars and createdAt
	 */
	@GetMapping(path = "/{owner}/{repositoryName}", produces = "application/json")
	public String getRepositoryDetails(@PathVariable(name = "owner", required = true) String owner,
			@PathVariable(name = "repositoryName", required = true) String repositoryName) {

		var response = repositoryDetailsService.getRepositoryDetails(owner, repositoryName);
		return response;
	}

}
