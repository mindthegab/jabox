package org.jabox.its.redmine;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.jabox.apis.its.ITSConnectorConfig;
import org.jabox.model.DeployerConfig;

@Entity
@DiscriminatorValue(RedmineRepository.ID)
public class RedmineRepositoryConfig extends DeployerConfig implements
		ITSConnectorConfig {
	private static final long serialVersionUID = -7682753903184815514L;

	public RedmineRepositoryConfig() {
		pluginId = RedmineRepository.ID;
	}
}
