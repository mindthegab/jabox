package org.jabox.application;

import java.io.File;
import java.io.IOException;

import org.apache.maven.artifact.InvalidRepositoryException;
import org.apache.maven.reactor.MavenExecutionException;
import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.persistence.provider.GeneralDao;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.jabox.apis.bts.BTSConnector;
import org.jabox.apis.bts.BTSManager;
import org.jabox.apis.cis.CISConnector;
import org.jabox.apis.cis.CISManager;
import org.jabox.apis.scm.SCMConnector;
import org.jabox.apis.scm.SCMException;
import org.jabox.apis.scm.SCMManager;
import org.jabox.model.Configuration;
import org.jabox.model.Project;
import org.tmatesoft.svn.core.SVNException;
import org.xml.sax.SAXException;

public class CreateProjectUtil {
	@SpringBean
	protected GeneralDao generalDao;
	@SpringBean
	protected SCMManager _scmManager;
	@SpringBean
	protected BTSManager _btsManager;
	@SpringBean
	protected CISManager _cisManager;

	public CreateProjectUtil() {
		InjectorHolder.getInjector().inject(this);
	}

	public void createProject(Project project) {
		try {
			createProjectMethod(project);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidRepositoryException e) {
			e.printStackTrace();
		} catch (MavenExecutionException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (SCMException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param project
	 * @throws IOException
	 * @throws InvalidRepositoryException
	 * @throws MavenExecutionException
	 * @throws SVNException
	 * @throws SAXException
	 * @throws SCMException
	 * @throws IOException
	 */
	private void createProjectMethod(Project project)
			throws InvalidRepositoryException, MavenExecutionException,
			SAXException, SCMException, IOException {
		final Configuration configuration = generalDao.getConfiguration();
		SCMConnector scm = _scmManager.getSCMConnectorInstance(configuration
				.getDefaultSCMConnector());

		System.out.println("Using SCM: " + scm.toString());
		File trunkDir = scm.createProjectDirectories(project);

		// Create Project from template.
		MavenCreateProject.createProjectWithMavenCore(project, trunkDir
				.getAbsolutePath());

		try {
			MavenConfigureDistributionManager
					.injectDistributionManager(new File(trunkDir, project
							.getName()
							+ "/pom.xml"));
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}
		// Commit Project
		scm.commitProject(project);
		project.setScmUrl(scm.getScmUrl() + "/" + project.getName() + "/trunk/"
				+ project.getName());

		// Create a directory structure in subversion for the project
		// svn.createProject(project);

		// Add files in the trunk.

		// Add Project in Issue Tracking System
		BTSConnector bts = _btsManager.getBTSConnectorInstance(configuration
				.getDefaultBTSConnector());
		if (bts != null) {
			// bts
			// .setUrl("http://localhost/cgi-bin/bugzilla/index.cgi?GoAheadAndLogIn=1");
			// bts.login("", "");
			bts.setUrl("http://localhost/redmine/");
			bts.login("admin", "admin123");
			bts.addProject(project);
			bts.addModule(project, project.getName(), "initial module",
					"myemail@gmail.com");
			bts.addVersion(project, "0.0.1");
		}

		CISConnector cis = _cisManager.getCISConnectorInstance(configuration
				.getDefaultCISConnector());
		if (cis != null) {
			cis.addProject(project);
		}
	}

}
