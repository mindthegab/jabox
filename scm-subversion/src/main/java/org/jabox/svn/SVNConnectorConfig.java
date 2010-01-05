package org.jabox.svn;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.jabox.model.DeployerConfig;

@Entity
@DiscriminatorValue(SVNConnector.ID)
public class SVNConnectorConfig extends DeployerConfig {
	private static final long serialVersionUID = 6542402958304063770L;

	public SVNConnectorConfig() {
		pluginId = SVNConnector.ID;
	}

	@Column(nullable = false)
	public String repositoryURL;

	public String username;

	public String password;

}
