package org.jabox.its.redmine;

import junit.framework.TestCase;

import org.jabox.apis.scm.SCMConnectorConfig;
import org.jabox.model.Project;
import org.jabox.model.Server;

/**
 * This is disabled by default. Credentials here are not real.
 */
public abstract class RedmineTest extends TestCase {

	public void testLoginValidCredentials() throws Exception {
		RedmineRepository redmineRepository = new RedmineRepository();
		Project project = new Project();
		project.setName("example");
		project.setDescription("example description");
		RedmineRepositoryConfig config = new RedmineRepositoryConfig();
		config.username = "myuser";
		config.password = "mypassword";
		Server server = new Server();
		server.setUrl("http://redmine.jabox.org/");
		config.setServer(server);
		boolean login = redmineRepository.login(config);
		assertTrue(login);
		redmineRepository.addProject(project, config);
		SCMConnectorConfig scmConfig = new SCMConnectorConfig() {
			private static final long serialVersionUID = 6864333280150931583L;

			@Override
			public Long getId() {
				return null;
			}

			@Override
			public Server getServer() {
				return null;
			}

			@Override
			public String getPluginId() {
				return null;
			}

			@Override
			public String getUsername() {
				return null;
			}

			@Override
			public String getScmUrl() {
				return "http://www.jabox.org/";
			}

			@Override
			public String getScmMavenPrefix() {
				return null;
			}

			@Override
			public String getProjectScmUrl(String projectName) {
				return null;
			}

			@Override
			public String getPassword() {
				return null;
			}
		};
		config.setAddRepositoryConfiguration(true);
		redmineRepository.addRepository(project, config, scmConfig,
				config.username, config.password);
	}

	public void testLoginInvalidCredentials() throws Exception {
		RedmineRepository redmineRepository = new RedmineRepository();
		Project project = new Project();
		project.setName("example");
		RedmineRepositoryConfig config = new RedmineRepositoryConfig();
		config.username = "myuser";
		config.password = "invalidpassword";
		Server server = new Server();
		server.setUrl("http://redmine.jabox.org/");
		config.setServer(server);
		boolean login = redmineRepository.login(config);
		assertFalse(login);
	}
}
