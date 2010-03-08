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
package org.jabox.scm.svn;

import java.io.File;

import org.jabox.model.Project;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNPropertyValue;
import org.tmatesoft.svn.core.auth.BasicAuthenticationManager;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.wc.ISVNPropertyHandler;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNCommitClient;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNWCClient;

public class SubversionFacade {
	private final SVNClientManager _clientManager;

	public SubversionFacade() {
		_clientManager = SVNClientManager.newInstance();

		if (!SubversionRepository.isInitialized()) {
			SubversionRepository.initialize();
		}

		FSRepositoryFactory.setup();
		SVNRepositoryFactoryImpl.setup();
		DAVRepositoryFactory.setup();
	}

	/**
	 * Checks out the base-dir of the subversion
	 * 
	 * @param storePath
	 *            the path where to store the subversion base-dir.
	 * @throws SVNException
	 */
	public void checkoutBaseDir(File storePath, ISVNConnectorConfig svnc)
			throws SVNException {

		_clientManager.createRepository(svnc.getSvnDir(), true);

		// Authenticate
		ISVNAuthenticationManager authManager = new BasicAuthenticationManager(
				svnc.getUsername(), svnc.getPassword());
		_clientManager.setAuthenticationManager(authManager);

		_clientManager.getUpdateClient().doCheckout(svnc.getSvnDir(),
				storePath, SVNRevision.UNDEFINED, SVNRevision.HEAD,
				SVNDepth.INFINITY, false);
	}

	public void commitProject(Project project, File tmpDir,
			ISVNConnectorConfig svnc) throws SVNException {
		// Add files (svn add)
		SVNWCClient wcClient = _clientManager.getWCClient();
		wcClient.doAdd(new File(tmpDir, project.getName()), false, false,
				false, SVNDepth.INFINITY, false, true);

		// Commit files (svn commit)
		SVNCommitClient commitClient = _clientManager.getCommitClient();
		ISVNAuthenticationManager authManager = new BasicAuthenticationManager(
				svnc.getUsername(), svnc.getPassword());
		_clientManager.setAuthenticationManager(authManager);
		File[] paths = new File[1];
		paths[0] = new File(tmpDir, project.getName());
		// paths[1] = new File("tags");
		// paths[2] = new File("branches");

		setProjectProperties(project, tmpDir);

		commitClient.doCommit(paths, false, "[JABOX] Initial Commit", false,
				true);
	}

	/**
	 * Set the bugtraq and svn:ignore properties to the project.
	 * 
	 * @param project
	 * @param rootDir
	 * @throws SVNException
	 */
	private void setProjectProperties(Project project, File rootDir)
			throws SVNException {
		File moduleFile = new File(rootDir, File.separator + project.getName()
				+ File.separator + "trunk" + File.separator + project.getName());
		setModuleProperties(moduleFile);
	}

	/**
	 * Set the bugtraq and snv:ignore properties to the module directory.
	 * 
	 * @param dir
	 * @throws SVNException
	 */
	private void setModuleProperties(File dir) throws SVNException {
		SVNWCClient wc = _clientManager.getWCClient();
		setSVNProperty(wc, dir, "bugtraq:number", "true");
		setSVNProperty(wc, dir, "bugtraq:append", "false");
		setSVNProperty(wc, dir, "bugtraq:message", "[ %BUGID% ]");
		setSVNProperty(wc, dir, "bugtraq:label", "Issue:");
		setSVNProperty(wc, dir, "bugtraq:url",
				"http://localhost/redmine/issues/show/%BUGID%");
		setSVNProperty(wc, dir, "bugtraq:warnifnoissue", "true");
		setSVNProperty(wc, dir, "svn:ignore", "target");
	}

	private void setSVNProperty(final SVNWCClient wc, final File dir,
			final String key, final String value) throws SVNException {
		wc.doSetProperty(dir, key, SVNPropertyValue.create(value), false,
				SVNDepth.EMPTY, ISVNPropertyHandler.NULL, null);
	}
}
