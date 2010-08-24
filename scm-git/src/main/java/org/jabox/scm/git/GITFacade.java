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
package org.jabox.scm.git;

import java.io.File;
import java.io.IOException;

import org.jabox.model.Project;

public class GITFacade {
	// private final SVNClientManager _clientManager;

	public GITFacade() {
	}

	public boolean validate(String url, String username, String password)
			throws IOException {
		// GITConnectorConfig svnc = new GITConnectorConfig();
		// svnc.username = username;
		// svnc.password = password;
		// svnc.server = new Server();
		// svnc.server.setUrl(url);
		// try {
		// // Authenticate
		// ISVNAuthenticationManager authManager = new
		// BasicAuthenticationManager(
		// svnc.getUsername(), svnc.getPassword());
		// SVNRepository repo = SVNRepositoryFactory.create(svnc.getSvnDir());
		// repo.setAuthenticationManager(authManager);
		// repo.testConnection();
		// return true;
		// } catch (SVNException e) {
		// return false;
		// }
		return true;
	}

	/**
	 * Checks out the base-dir of the git
	 * 
	 * @param storePath
	 *            the path where to store the git base-dir.
	 */
	public void checkoutBaseDir(final File storePath,
			final IGITConnectorConfig svnc) {

		GITRepository.initialize();
		// _clientManager.createRepository(svnc.getSvnDir(), true);
		//
		// // Authenticate
		// ISVNAuthenticationManager authManager = new
		// BasicAuthenticationManager(
		// svnc.getUsername(), svnc.getPassword());
		// _clientManager.setAuthenticationManager(authManager);
		//
		// _clientManager.getUpdateClient().doCheckout(svnc.getSvnDir(),
		// storePath, SVNRevision.UNDEFINED, SVNRevision.HEAD,
		// SVNDepth.INFINITY, false);
	}

	public void commitProject(final Project project, final File tmpDir,
			final IGITConnectorConfig svnc) {
		// // Add files (svn add)
		// SVNWCClient wcClient = _clientManager.getWCClient();
		// wcClient.doAdd(new File(tmpDir, project.getName()), false, false,
		// false, SVNDepth.INFINITY, false, true);
		//
		// // Commit files (svn commit)
		// SVNCommitClient commitClient = _clientManager.getCommitClient();
		// ISVNAuthenticationManager authManager = new
		// BasicAuthenticationManager(
		// svnc.getUsername(), svnc.getPassword());
		// _clientManager.setAuthenticationManager(authManager);
		// File[] paths = new File[1];
		// paths[0] = new File(tmpDir, project.getName());
		// // paths[1] = new File("tags");
		// // paths[2] = new File("branches");
		//
		// setProjectProperties(project, tmpDir);
		//
		// commitClient.doCommit(paths, false, "[JABOX] Initial Commit", null,
		// null, false, true, SVNDepth.INFINITY);
	}

	/**
	 * Set the bugtraq and svn:ignore properties to the project.
	 * 
	 * @param project
	 * @param rootDir
	 * @throws SVNException
	 */
	private void setProjectProperties(final Project project, final File rootDir) {
		// File moduleFile = new File(rootDir, File.separator +
		// project.getName()
		// + File.separator + "trunk" + File.separator + project.getName());
		// setModuleProperties(moduleFile);
	}

	/**
	 * Set the bugtraq and snv:ignore properties to the module directory.
	 * 
	 * @param dir
	 * @throws SVNException
	 */
	private void setModuleProperties(final File dir) {
		// SVNWCClient wc = _clientManager.getWCClient();
		// // setSVNProperty(wc, dir, "bugtraq:number", "true");
		// // setSVNProperty(wc, dir, "bugtraq:append", "false");
		// // setSVNProperty(wc, dir, "bugtraq:message", "refs %BUGID%");
		// // setSVNProperty(wc, dir, "bugtraq:label", "Issue:");
		// // setSVNProperty(wc, dir, "bugtraq:url",
		// // "http://localhost/redmine/issues/show/%BUGID%");
		// // setSVNProperty(wc, dir, "bugtraq:warnifnoissue", "true");
		// setSVNProperty(wc, dir, "svn:ignore", "target");
	}

	// private void setSVNProperty(final SVNWCClient wc, final File dir,
	// final String key, final String value) throws SVNException {
	// wc.doSetProperty(dir, key, SVNPropertyValue.create(value), false,
	// SVNDepth.EMPTY, ISVNPropertyHandler.NULL, null);
	// }
}
