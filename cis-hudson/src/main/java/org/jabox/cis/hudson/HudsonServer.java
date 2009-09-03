package org.jabox.cis.hudson;

import org.jabox.apis.embedded.AbstractEmbeddedServer;
import org.jabox.maven.helper.MavenDownloader;

public class HudsonServer extends AbstractEmbeddedServer {

	@Override
	public String getServerName() {
		return "hudson";
	}

	@Override
	public String getWarPath() {
		return MavenDownloader.downloadArtifact("org.jvnet.hudson.main",
				"hudson-war", "1.322", "war").getAbsolutePath();
	}
}
