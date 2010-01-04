package org.jabox.mrm.artifactory;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.jabox.model.DeployerConfig;

@Entity
@DiscriminatorValue(ArtifactoryConnector.ID)
public class ArtifactoryConnectorConfig extends DeployerConfig {
	private static final long serialVersionUID = 6050853799588453919L;
	public ArtifactoryConnectorConfig()
    {
        pluginId = ArtifactoryConnector.ID;
    }

    @Column(nullable = false)
    public String host;

    public Integer port;

}
