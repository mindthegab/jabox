package org.jabox.cis.hudson;

import org.jabox.apis.embedded.AbstractEmbeddedServer;

public class HudsonServer extends AbstractEmbeddedServer {

	@Override
	public String getServerName() {
		return "hudson";
	}

	@Override
	public String getWarPath() {
		return "E:/Documents and Settings/Administrator/.m2/repository/org/jvnet/hudson/main/hudson-war/1.319/hudson-war-1.319.war";
	}
}
