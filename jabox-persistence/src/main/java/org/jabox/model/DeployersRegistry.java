package org.jabox.model;

import org.springframework.stereotype.Service;

@Service
public class DeployersRegistry extends IdentifiableBeanRegistry<DeployerPlugin,String>
{

    public DeployersRegistry()
    {
        super(DeployerPlugin.class);
    }

}
