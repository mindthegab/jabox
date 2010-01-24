package org.jabox.scm.svn;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;

public interface ISVNConnectorConfig {
	public SVNURL getSvnDir() throws SVNException;

	public String getUsername();

	public String getPassword();
}
