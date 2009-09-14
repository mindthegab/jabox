package org.jabox.cis.hudson;

import org.jabox.apis.embedded.AbstractEmbeddedServer;
import org.jabox.maven.helper.MavenDownloader;

public class HudsonServer extends AbstractEmbeddedServer {

	private static final String GROUP_ID = "org.jvnet.hudson.main";
	private static final String ARTIFACT_ID = "hudson-war";
	private static final String VERSION = "1.322";
	private static final String TYPE = "war";

	@Override
	public String getServerName() {
		return "hudson";
	}

	@Override
	public String getWarPath() {
		return MavenDownloader.downloadArtifact(GROUP_ID, ARTIFACT_ID, VERSION,
				TYPE).getAbsolutePath();
	}
}
