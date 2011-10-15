package org.jabox.model;

import java.util.List;

import org.jabox.apis.Connector;

public interface IDeployersRegistry {

	public abstract Connector getEntry(String pluginId);

	public abstract List<? extends String> getIds(
			Class<? extends Connector> connectorClass);

}