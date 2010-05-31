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
package org.jabox.facades.svn;

import java.io.File;

import junit.framework.TestCase;

import org.jabox.scm.svn.SubversionRepository;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNRevision;

public abstract class SubversionFacadeTest extends TestCase {
	private SVNClientManager _clientManager;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		_clientManager = SVNClientManager.newInstance();
	}

	public void test1() throws SVNException {
		// SVNCommitClient commitClient = _clientManager.getCommitClient();
		FSRepositoryFactory.setup();

		// SVNURL svnurl = SVNURL
		// .parseURIEncoded("file:///home/dimitris/svn/repos/helloworld");
		// SVNURL[] svnurls = new SVNURL[] { svnurl };

		// commitClient.doMkDir(svnurls, "[JABOX] Added Project Directory");
	}

	public void test2() throws SVNException {
		String tgtPath = "target/svnRepoTest/";
		SVNRepositoryFactory.createLocalRepository(new File(tgtPath), true,
				false);
	}

	public void testAddFiles() throws SVNException {
		// SVNCommitClient commitClient = _clientManager.getCommitClient();
		// SVNWCClient wcClient = _clientManager.getWCClient();

		SVNURL svnTestingDir = SVNURL.fromFile(SubversionRepository
				.getSubversionBaseDir());

		_clientManager.createRepository(svnTestingDir, true);
		svnTestingDir = svnTestingDir.appendPath("/foobar2/", true);
		File dstPath = new File("c:/home/dimitris/tmp/foobar/");
		dstPath.mkdirs();
		_clientManager.getUpdateClient().doCheckout(svnTestingDir, dstPath,
				SVNRevision.HEAD, SVNRevision.HEAD, SVNDepth.INFINITY, false);
	}
}
