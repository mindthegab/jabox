/*
 * The MIT License
 *
 * Copyright (c) 2009 Dimitris Kapanidis
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
