package org.jabox.embedded.jetty;

import org.jabox.apis.embedded.EmbeddedServer;
import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.bio.SocketConnector;
import org.mortbay.jetty.webapp.WebAppContext;

public class Start {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Server server = new Server();
		SocketConnector connector = new SocketConnector();
		// Set some timeout options to make debugging easier.
		connector.setMaxIdleTime(1000 * 60 * 60);
		connector.setSoLingerTime(-1);
		connector.setPort(9090);
		server.setConnectors(new Connector[] { connector });

		EmbeddedServer es = (EmbeddedServer) Class.forName(
				"org.jabox.cis.hudson.HudsonServer").newInstance();
		es.addWebAppContext(server);

		EmbeddedServer es2 = (EmbeddedServer) Class.forName(
				"org.jabox.mrm.nexus.NexusServer").newInstance();
		es2.addWebAppContext(server);

		// EmbeddedServer es3 = (EmbeddedServer) Class.forName(
		// "org.jabox.ide.eclipse.EclipseJNLPServer").newInstance();
		// es3.addWebAppContext(server);

		// Adding ROOT handler.
		// NOTE: This should be added last on server.
		WebAppContext bb = new WebAppContext();
		bb.setServer(server);
		bb.setContextPath("/");
		bb.setWar("src/main/webapp");
		server.addHandler(bb);

		try {
			System.out
					.println(">>> STARTING EMBEDDED JETTY SERVER, PRESS ANY KEY TO STOP");
			server.start();

			while (System.in.available() == 0) {
				Thread.sleep(5000);
			}
			server.stop();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(100);
		}
	}
}
