package org.jabox.ide.eclipse;

import org.jabox.apis.embedded.AbstractEmbeddedServer;
import org.jabox.maven.helper.MavenDownloader;

public class EclipseJNLPServer extends AbstractEmbeddedServer {

	public String getServerName() {
		return "eclipse";
	}

	public String getWarPath() {
		return MavenDownloader.downloadArtifact("org.jabox", "eclipse-webapp",
				"1.0.0-SNAPSHOT", "war").getAbsolutePath();
	}
}
