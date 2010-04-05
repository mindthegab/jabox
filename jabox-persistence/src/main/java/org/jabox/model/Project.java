/*
 * Jabox Open Source Version
 * Copyright (C) 2009-2010 Dimitris Kapanidis                                                                                                                          
 * 
 * This file is part of Jabox
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 */
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

	public void setMavenArchetype(final MavenArchetype mavenArchetype) {
		_mavenArchetype = mavenArchetype;
	}

	public void setName(final String name) {
		_name = name;
	}

	public String getName() {
		return _name;
	}

	@Override
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
