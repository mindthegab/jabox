package org.jabox.apis.embedded;

import java.io.File;

import org.jabox.environment.Environment;
import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.bio.SocketConnector;
import org.mortbay.jetty.webapp.WebAppContext;

public abstract class AbstractEmbeddedServer implements EmbeddedServer {
	private Server _server;

	public void startServerAndWait() {
		startServer();
		try {
			while (System.in.available() == 0) {
				Thread.sleep(5000);
			}
			_server.stop();
			_server.join();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(100);
		}
	}

	/**
	 * 
	 * @return The server Name that will be used as contextPath on the Servlet
	 *         container.
	 */
	public abstract String getServerName();

	public void addWebAppContext(Server server) {
		WebAppContext wac = new WebAppContext();
		wac.setServer(_server);
		wac.setContextPath("/" + getServerName());
		wac.setWar(getWarPath());
		wac.setTempDirectory(new File(Environment.getBaseDir(), "server-"
				+ getServerName()));
		server.addHandler(wac);
	}

	/**
	 * 
	 * @return the absolute path of the war file.
	 */
	public abstract String getWarPath();

	public void startServer() {
		// System.setProperty("HUDSON_HOME", "c://");
		// Main.run(new String[]{"-DHUDSON_HOME=c:"});
		// new Main().run(args);

		_server = new Server();
		SocketConnector connector = new SocketConnector();
		// Set some timeout options to make debugging easier.
		connector.setMaxIdleTime(1000 * 60 * 60);
		connector.setSoLingerTime(-1);
		connector.setPort(9092);
		_server.setConnectors(new Connector[] { connector });

		addWebAppContext(_server);
		try {
			System.out
					.println(">>> STARTING EMBEDDED JETTY SERVER, PRESS ANY KEY TO STOP");
			_server.start();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(100);
		}

	}
}
