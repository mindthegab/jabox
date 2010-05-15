package org.jabox.its.redmine;

import org.jabox.model.Project;
import org.jabox.model.Server;

import junit.framework.TestCase;

public class RedmineTest extends TestCase {

	public void testname() throws Exception {
		RedmineRepository redmineRepository = new RedmineRepository();
		Project project = new Project();
		project.setName("example");
		RedmineRepositoryConfig config = new RedmineRepositoryConfig();
		config.password="admin";
		config.username="admin123";
		Server server = new Server();
		server.setUrl("http://localhost:8080/");
		config.setServer(server);
		redmineRepository.login(config);
		redmineRepository.addProject(project, config);
	}

}
