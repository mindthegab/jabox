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
package org.jabox.application;

import java.io.File;
import java.io.IOException;

import org.apache.maven.artifact.InvalidRepositoryException;
import org.apache.maven.reactor.MavenExecutionException;
import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.persistence.provider.GeneralDao;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.jabox.apis.Manager;
import org.jabox.apis.cis.CISConnector;
import org.jabox.apis.its.ITSConnector;
import org.jabox.apis.its.ITSConnectorConfig;
import org.jabox.apis.rms.RMSConnector;
import org.jabox.apis.scm.SCMConnector;
import org.jabox.apis.scm.SCMConnectorConfig;
import org.jabox.apis.scm.SCMException;
import org.jabox.model.DefaultConfiguration;
import org.jabox.model.Project;
import org.tmatesoft.svn.core.SVNException;
import org.xml.sax.SAXException;

public class CreateProjectUtil {
	@SpringBean
	protected GeneralDao generalDao;

	@SpringBean
	protected Manager<ITSConnector> _itsManager;

	@SpringBean
	protected Manager<SCMConnector<SCMConnectorConfig>> _scmManager;

	@SpringBean
	protected Manager<CISConnector> _cisManager;

	@SpringBean
	protected Manager<RMSConnector> _rmsManager;

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
		final DefaultConfiguration dc = generalDao.getDefaultConfiguration();

		SCMConnectorConfig scmc = dc.getScm();

		SCMConnector<SCMConnectorConfig> scm = _scmManager
				.getConnectorInstance(scmc);

		System.out.println("Using SCM: " + scm.toString());
		File trunkDir = scm.createProjectDirectories(project, scmc);

		// Create Project from template.
		MavenCreateProject.createProjectWithMavenCore(project, trunkDir
				.getAbsolutePath());

		RMSConnector rms = _rmsManager.getConnectorInstance(dc.getRms());

		if (rms != null) {
			try {
				File pomXml = new File(trunkDir, project.getName() + "/pom.xml");
				MavenConfigureDistributionManager.injectDistributionManager(
						pomXml, dc.getRms());
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}
		}

		// Commit Project
		scm.commitProject(project, scmc);
		project.setScmUrl(scm.getScmUrl() + "/" + project.getName() + "/trunk/"
				+ project.getName());

		// Create a directory structure in subversion for the project
		// svn.createProject(project);

		// Add files in the trunk.

		// Add Project in Issue Tracking System
		ITSConnectorConfig config = dc.getIts();
		ITSConnector its = _itsManager.getConnectorInstance(config);
		if (its != null) {
			// its
			// .setUrl("http://localhost/cgi-bin/bugzilla/index.cgi?GoAheadAndLogIn=1");
			// its.login("", "");

			// its.setUrl("http://localhost/redmine");
			its.login("admin", "admin123", config);
			its.addProject(project, config);
			its.addModule(project, config, project.getName(), "initial module",
					"myemail@gmail.com");
			its.addVersion(project, config, "0.0.1");
		}

		CISConnector cis = (CISConnector) _itsManager.getConnectorInstance(dc
				.getCis());
		if (cis != null) {
			cis.addProject(project, dc.getCis());
		}
	}

}
