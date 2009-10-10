package org.jabox.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue(FtpDeployerPlugin.ID)
public class FtpDeployerConfig extends DeployerConfig
{
	private static final long serialVersionUID = -5318749273535543344L;

	public FtpDeployerConfig()
    {
        pluginId = FtpDeployerPlugin.ID;
    }

    @Column(nullable = false)
    public String host;

    public Integer port;
}
