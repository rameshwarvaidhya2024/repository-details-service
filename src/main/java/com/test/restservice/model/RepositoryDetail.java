package com.test.restservice.model;

import org.springframework.stereotype.Component;

/**
 * The Class RepositoryDetail represents the response from GIT repos API
 * response. RestClient make API calls and map the response
 */
@Component
public class RepositoryDetail {

	private String full_name;

	private String description;

	private String clone_url;

	private int stargazers_count;

	private String created_at;

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getClone_url() {
		return clone_url;
	}

	public void setClone_url(String clone_url) {
		this.clone_url = clone_url;
	}

	public int getStargazers_count() {
		return stargazers_count;
	}

	public void setStargazers_count(int stargazers_count) {
		this.stargazers_count = stargazers_count;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	@Override
	public String toString() {
		return "RepositoryDetail [full_name=" + full_name + ", description=" + description + ", clone_url=" + clone_url
				+ ", stargazers_count=" + stargazers_count + ", created_at=" + created_at + "]";
	}

}