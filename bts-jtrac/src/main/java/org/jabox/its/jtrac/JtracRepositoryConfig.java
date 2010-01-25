package org.jabox.its.jtrac;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.jabox.apis.its.ITSConnectorConfig;
import org.jabox.model.DeployerConfig;

@Entity
@DiscriminatorValue(JtracRepository.ID)
public class JtracRepositoryConfig extends DeployerConfig implements
		ITSConnectorConfig {
	private static final long serialVersionUID = -3304041364131581981L;

	public JtracRepositoryConfig() {
		pluginId = JtracRepository.ID;
	}
}
