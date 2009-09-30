package org.jabox.mrm.artifactory;

import org.jabox.apis.rms.RMSConnector;
import org.springframework.stereotype.Service;

@Service
public class ArtifactoryConnector implements RMSConnector {

	@Override
	public String toString() {
		return "Artifactory Plugin";
	}

	public String getReleaseRepositoryURL() {
		return "http://localhost:9090/artifactory/libs-releases-local";
	}

	public String getSnapshotsRepositoryURL() {
		return "http://localhost:9090/artifactory/libs-snapshots-local";
	}
}
