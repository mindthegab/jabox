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

import javax.persistence.Entity;

import org.apache.wicket.persistence.domain.BaseEntity;

@Entity
public class MavenArchetype extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1354396598525488609L;

	private String _groupId;
	private String _artifactId;
	private String _version;

	public MavenArchetype() {
	}

	public MavenArchetype(final String groupId, final String artifactId,
			final String version) {
		_groupId = groupId;
		_artifactId = artifactId;
		_version = version;
	}

	public String getArchetypeGroupId() {
		return _groupId;
	}

	public String getArchetypeArtifactId() {
		return _artifactId;
	}

	public String getArchetypeVersion() {
		return _version;
	}

	@Override
	public String toString() {
		return _groupId + ":" + _artifactId + ":" + _version;
	}
}
