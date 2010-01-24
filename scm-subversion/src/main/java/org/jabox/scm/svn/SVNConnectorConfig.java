package org.jabox.scm.svn;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.jabox.apis.scm.SCMConnectorConfig;
import org.jabox.model.DeployerConfig;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;

@Entity
@DiscriminatorValue(SVNConnector.ID)
public class SVNConnectorConfig extends DeployerConfig implements
		SCMConnectorConfig, ISVNConnectorConfig {
	private static final long serialVersionUID = 6542402958304063770L;

	public SVNConnectorConfig() {
		pluginId = SVNConnector.ID;
	}

	public String username;

	public String password;

	public SVNURL getSvnDir() throws SVNException {
		return SVNURL.parseURIEncoded(getServer().getUrl());
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}
