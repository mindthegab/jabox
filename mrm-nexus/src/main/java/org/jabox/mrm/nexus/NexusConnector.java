package org.jabox.mrm.nexus;

import org.jabox.apis.rms.RMSConnector;
import org.springframework.stereotype.Service;

@Service
public class NexusConnector implements RMSConnector {

	@Override
	public String toString() {
		return "Nexus Plugin";
	}

	public String getReleaseRepositoryURL() {
		return "http://localhost:9090/nexus/content/repositories/releases/";
	}

	public String getSnapshotsRepositoryURL() {
		return "http://localhost:9090/nexus/content/repositories/snapshots/";
	}
}
