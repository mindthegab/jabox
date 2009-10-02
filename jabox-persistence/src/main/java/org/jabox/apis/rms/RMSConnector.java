package org.jabox.apis.rms;

import org.jabox.apis.Connector;


public interface RMSConnector extends Connector{

	public String getSnapshotsRepositoryURL();

	public String getReleaseRepositoryURL();
}
