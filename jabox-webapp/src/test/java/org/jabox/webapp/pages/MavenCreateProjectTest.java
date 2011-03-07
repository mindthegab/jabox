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
package org.jabox.webapp.pages;

import java.io.IOException;

import junit.framework.TestCase;

import org.apache.maven.MavenExecutionException;
import org.apache.maven.artifact.InvalidRepositoryException;
import org.jabox.application.MavenCreateProject;
import org.jabox.model.MavenArchetype;
import org.jabox.model.Project;

public class MavenCreateProjectTest extends TestCase {

	public void testCreateProject() throws IOException {

		Project project = new Project();
		project.setName("helloWorld");

		// MavenCreateProject.createProject(project);
	}

	public void testCreateProjectWithMavenCore() throws IOException,
			InvalidRepositoryException, MavenExecutionException {

		Project project = new Project();
		project.setName("helloWorld");

		MavenArchetype ma = new MavenArchetype("org.apache.wicket",
				"wicket-archetype-quickstart", "1.3.3");
		project.setMavenArchetype(ma);
		 MavenCreateProject.createProjectWithMavenCore(project, "C:/tmp/");
	}
}
