package org.jabox.scm.svn;

import org.jabox.apis.scm.SCMConnectorConfig;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;

public interface ISVNConnectorConfig extends SCMConnectorConfig {
	public SVNURL getSvnDir() throws SVNException;

	public String getUsername();

	public String getPassword();
}
