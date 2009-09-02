package org.jabox.facades.svn;

import java.io.File;

import junit.framework.TestCase;

import org.jabox.svn.SubversionRepository;
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

	protected void setUp() throws Exception {
		super.setUp();
		_clientManager = SVNClientManager.newInstance();
	}

	public void test1() throws SVNException {
		SVNCommitClient commitClient = _clientManager.getCommitClient();
		FSRepositoryFactory.setup();

		SVNURL svnurl = SVNURL
				.parseURIEncoded("file:///home/dimitris/svn/repos/helloworld");
		SVNURL[] svnurls = new SVNURL[] { svnurl };

//		commitClient.doMkDir(svnurls, "[JABOX] Added Project Directory");
	}

	public void test2() throws SVNException {

		try {
			String tgtPath = "/home/dimitris/svn/repos2";
			SVNURL tgtURL = SVNRepositoryFactory.createLocalRepository(
					new File(tgtPath), true, false);
		} catch (SVNException e) {
			// handle exception
		}
	}

	public void testAddFiles() throws SVNException {
		SVNCommitClient commitClient = _clientManager.getCommitClient();
		SVNWCClient wcClient = _clientManager.getWCClient();

		SVNURL svnTestingDir = SVNURL.parseURIDecoded("file://"
				+ SubversionRepository.getSubversionBaseDir());
		_clientManager.createRepository(svnTestingDir, true);

		_clientManager.getUpdateClient().doCheckout(svnTestingDir,
				new File("/home/dimitris/tmp/foobar"), SVNRevision.HEAD,
				SVNRevision.HEAD, false);

	
	}
}
