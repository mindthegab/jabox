package org.jabox.its.redmine;

import org.jabox.model.Project;
import org.jabox.model.Server;

import junit.framework.TestCase;

public abstract class RedmineTest extends TestCase{
	
	public void testname() throws Exception {
		RedmineRepository redmineRepository = new RedmineRepository();
		Project project= new Project();
		project.setName("example");
		RedmineRepositoryConfig config = new RedmineRepositoryConfig();
		Server server = new Server();
		server.setUrl("http://localhost/redmine/");
		config.setServer(server );
		redmineRepository.addProject(project, config );
	}

}
