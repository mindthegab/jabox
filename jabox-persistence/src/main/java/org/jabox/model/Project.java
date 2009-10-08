package org.jabox.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.apache.wicket.persistence.domain.BaseEntity;

/**
 * A Project.
 * 
 * @author dimitris
 */
@Entity
public class Project extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String _name;
	private String _description;
	private String _scmUrl;

	@Column(nullable = false, length = 1024)
	private MavenArchetype _mavenArchetype;

	public MavenArchetype getMavenArchetype() {
		return _mavenArchetype;
	}

	public void setMavenArchetype(MavenArchetype mavenArchetype) {
		_mavenArchetype = mavenArchetype;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getName() {
		return _name;
	}

	public String toString() {
		return "Project: " + _name;
	}

	public void setDescription(final String description) {
		_description = description;
	}

	public String getDescription() {
		return _description;
	}

	public void setScmUrl(final String scmUrl) {
		_scmUrl = scmUrl;
	}

	public String getScmUrl() {
		return _scmUrl;
	}

}
