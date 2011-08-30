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
	private String name;
	private String description;
	private String scmUrl;
	private String sourceEncoding = "UTF-8";
	private boolean signArtifactReleases = false;

	@Column(nullable = false, length = 1024)
	private MavenArchetype mavenArchetype;
	private String scmMavenPrefix;

	public MavenArchetype getMavenArchetype() {
		return mavenArchetype;
	}

	public void setMavenArchetype(final MavenArchetype mavenArchetype) {
		this.mavenArchetype = mavenArchetype;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setScmUrl(final String scmUrl) {
		this.scmUrl = scmUrl;
	}

	public String getScmUrl() {
		return scmUrl;
	}

	/**
	 * Returns an encoded url for use in maven's pom.xml. Examples of these
	 * formats:
	 * <p>
	 * scm:svn:file:///svn/root/module
	 * scm:svn:file://localhost/path_to_repository
	 * scm:svn:file://my_server/path_to_repository
	 * scm:svn:http://svn.apache.org/svn/root/module
	 * scm:svn:https://username@svn.apache.org/svn/root/module
	 * scm:svn:https://username:password@svn.apache.org/svn/root/module
	 * <p>
	 * More information can be found here:
	 * http://maven.apache.org/scm/scms-overview.html
	 * 
	 * @return SCM url encoded for maven's pom.xml
	 */
	public String getScmMavenUrl() {
		return getScmMavenPrefix() + getScmUrl();
	}

	public void setSourceEncoding(String sourceEncoding) {
		this.sourceEncoding = sourceEncoding;
	}

	public String getSourceEncoding() {
		return sourceEncoding;
	}

	public void setSignArtifactReleases(boolean signArtifactReleases) {
		this.signArtifactReleases = signArtifactReleases;
	}

	public boolean isSignArtifactReleases() {
		return signArtifactReleases;
	}

	public void setScmMavenPrefix(final String scmMavenPrefix) {
		this.scmMavenPrefix = scmMavenPrefix;
	}

	public String getScmMavenPrefix() {
		return scmMavenPrefix;
	}
}
