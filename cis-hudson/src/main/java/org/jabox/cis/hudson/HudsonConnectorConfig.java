package org.jabox.cis.hudson;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.jabox.apis.cis.CISConnectorConfig;
import org.jabox.model.DeployerConfig;

@Entity
@DiscriminatorValue(HudsonConnector.ID)
public class HudsonConnectorConfig extends DeployerConfig implements
		CISConnectorConfig {
	private static final long serialVersionUID = -6696934779273872749L;

	public String username;

	public String password;

	public HudsonConnectorConfig() {
		pluginId = HudsonConnector.ID;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
