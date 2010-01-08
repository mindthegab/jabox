package org.jabox.bts.redmine;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.jabox.apis.bts.ITSConnectorConfig;
import org.jabox.model.DeployerConfig;

@Entity
@DiscriminatorValue(RedmineRepository.ID)
public class RedmineRepositoryConfig extends DeployerConfig implements
		ITSConnectorConfig {
	private static final long serialVersionUID = -7682753903184815514L;

	public RedmineRepositoryConfig() {
		pluginId = RedmineRepository.ID;
	}

	@Column(nullable = false)
	public String host;

	public Integer port;

}
