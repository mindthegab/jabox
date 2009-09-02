package org.jabox.ide.eclipse;

import org.jabox.apis.embedded.AbstractEmbeddedServer;

public class EclipseJNLPServer extends AbstractEmbeddedServer {

	public String getServerName() {
		return "eclipse";
	}

	public String getWarPath() {
		return "E:/Documents and Settings/Administrator/.m2/repository/org/jabox/eclipse-webapp/0.0.1-SNAPSHOT/eclipse-webapp-0.0.1-SNAPSHOT.war";
	}
}
