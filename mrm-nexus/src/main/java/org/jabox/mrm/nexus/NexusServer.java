package org.jabox.mrm.nexus;

import org.jabox.apis.embedded.AbstractEmbeddedServer;
import org.jabox.maven.helper.MavenDownloader;

/**
 * 
 */
public class NexusServer extends AbstractEmbeddedServer {

	public static void main(String[] args) throws Exception {
		new NexusServer().startServerAndWait();
	}

	public String getServerName() {
		return "nexus";
	}

	public String getWarPath() {
		return MavenDownloader.downloadArtifact("org.sonatype.nexus",
				"nexus-webapp", "1.3.6", "war").getAbsolutePath();
	}
}
