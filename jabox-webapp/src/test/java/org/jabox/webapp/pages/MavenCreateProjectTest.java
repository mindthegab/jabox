package org.jabox.webapp.pages;

import java.io.IOException;

import junit.framework.TestCase;

import org.apache.maven.artifact.InvalidRepositoryException;
import org.apache.maven.reactor.MavenExecutionException;
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

		// MavenCreateProject.createProjectWithMavenCore(project);
	}

}
