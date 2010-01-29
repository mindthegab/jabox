package org.jabox.mrm.artifactory;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.jabox.apis.rms.RMSConnectorConfig;
import org.jabox.model.DeployerConfig;

@Entity
@DiscriminatorValue(ArtifactoryConnector.ID)
public class ArtifactoryConnectorConfig extends DeployerConfig implements
		RMSConnectorConfig {
	private static final long serialVersionUID = 6050853799588453919L;

	public ArtifactoryConnectorConfig() {
		pluginId = ArtifactoryConnector.ID;
	}

	public String getReleaseRepositoryURL() {
		return getServer().getUrl() + "libs-releases-local";
	}

	public String getSnapshotsRepositoryURL() {
		return getServer().getUrl() + "libs-snapshots-local";
	}
}
