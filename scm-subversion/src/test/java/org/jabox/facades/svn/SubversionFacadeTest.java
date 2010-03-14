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
import org.tmatesoft.svn.core.wc.SVNCommitClient;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNWCClient;

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

		try {
			String tgtPath = "c:///home/dimitris/svn/repos2";
			SVNURL tgtURL = SVNRepositoryFactory.createLocalRepository(
					new File(tgtPath), true, false);
		} catch (SVNException e) {
			// handle exception
		}
	}

	public void testAddFiles() throws SVNException {
		SVNCommitClient commitClient = _clientManager.getCommitClient();
		SVNWCClient wcClient = _clientManager.getWCClient();

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
