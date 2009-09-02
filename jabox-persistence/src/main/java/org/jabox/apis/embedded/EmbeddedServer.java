package org.jabox.apis.embedded;

import org.mortbay.jetty.Server;

public interface EmbeddedServer {

	void startServer();

	void addWebAppContext(Server server);
}
