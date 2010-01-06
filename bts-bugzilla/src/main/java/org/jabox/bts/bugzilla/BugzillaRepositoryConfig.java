package org.jabox.bts.bugzilla;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.jabox.apis.rms.RMSConnectorConfig;
import org.jabox.model.DeployerConfig;

@Entity
@DiscriminatorValue(BugzillaRepository.ID)
public class BugzillaRepositoryConfig extends DeployerConfig implements
		RMSConnectorConfig {
	private static final long serialVersionUID = -4830931405902901630L;

	public BugzillaRepositoryConfig() {
		pluginId = BugzillaRepository.ID;
	}

	@Column(nullable = false)
	public String host;

	public Integer port;

}
