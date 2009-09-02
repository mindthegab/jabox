package org.jabox.mrm.nexus;

import org.jabox.apis.embedded.AbstractEmbeddedServer;

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
		return "E:/Documents and Settings/Administrator/.m2/repository/org/sonatype/nexus/nexus-webapp/1.3.6/nexus-webapp-1.3.6.war";
	}
}
