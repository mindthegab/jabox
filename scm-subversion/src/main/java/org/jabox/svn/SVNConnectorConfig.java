package org.jabox.svn;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.jabox.apis.scm.SCMConnectorConfig;
import org.jabox.model.DeployerConfig;

@Entity
@DiscriminatorValue(SVNConnector.ID)
public class SVNConnectorConfig extends DeployerConfig implements
		SCMConnectorConfig {
	private static final long serialVersionUID = 6542402958304063770L;

	public SVNConnectorConfig() {
		pluginId = SVNConnector.ID;
	}

	public String username;

	public String password;

	public boolean embedded;

}
