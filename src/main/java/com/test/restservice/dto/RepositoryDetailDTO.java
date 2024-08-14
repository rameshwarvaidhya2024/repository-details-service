package com.test.restservice.dto;

import org.springframework.stereotype.Component;

/**
 * The Class RepositoryDetailDTO represents response to the client. Message
 * mapper maps the response from GIT API
 */
@Component
public class RepositoryDetailDTO {

	private String fullName;

	private String description;

	private String cloneUrl;

	private int stars;

	private String createdAt;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCloneUrl() {
		return cloneUrl;
	}

	public void setCloneUrl(String cloneUrl) {
		this.cloneUrl = cloneUrl;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "RepositoryDetailDTO [fullName=" + fullName + ", description=" + description + ", cloneUrl=" + cloneUrl
				+ ", stars=" + stars + ", createdAt=" + createdAt + "]";
	}

}
