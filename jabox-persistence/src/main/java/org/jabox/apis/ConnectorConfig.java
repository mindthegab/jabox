package org.jabox.apis;

import org.jabox.model.Identifiable;
import org.jabox.model.Server;

public interface ConnectorConfig extends Identifiable<Long>, IBaseEntity {

	public String getPluginId();

	public Server getServer();
}
