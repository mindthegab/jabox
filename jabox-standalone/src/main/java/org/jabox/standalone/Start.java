package org.jabox.standalone;

import java.net.URL;

import org.jabox.apis.embedded.EmbeddedServer;
import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.bio.SocketConnector;
import org.mortbay.jetty.webapp.WebAppContext;
import org.mortbay.resource.Resource;

public class Start {
	private static final String PACKAGE = "/jabox-webapp/";

	public Start() {
		startEmbeddedJetty(false);
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		System.out.println(getJaboxWebapp());
		startEmbeddedJetty(true);
	}

	private static String getJaboxWebapp() {
		Resource res = Resource.newClassPathResource(PACKAGE);
		return res.toString();
	}

	/**
	 * 
	 * @param startJabox
	 *            If set to true the Jetty application is starting Jabox
	 *            Application together with the embeddedServers.
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public static void startEmbeddedJetty(final boolean startJabox) {
		Server server = new Server();
		SocketConnector connector = new SocketConnector();
		// Set some timeout options to make debugging easier.
		connector.setMaxIdleTime(1000 * 60 * 60);
		connector.setSoLingerTime(-1);
		connector.setPort(9090);
		server.setConnectors(new Connector[] { connector });
		try {
			addEmbeddedServer(server, "org.jabox.cis.hudson.HudsonServer");
			addEmbeddedServer(server, "org.jabox.mrm.nexus.NexusServer");
			addEmbeddedServer(server, "org.jabox.ide.eclipse.EclipseJNLPServer");

			if (startJabox) {
				// Adding ROOT handler.
				// NOTE: This should be added last on server.
				WebAppContext bb = new WebAppContext();
				bb.setServer(server);
				bb.setContextPath("/");
				bb.setWar(getJaboxWebapp());
				server.addHandler(bb);
			}

			System.out
					.println(">>> STARTING EMBEDDED JETTY SERVER, PRESS ANY KEY TO STOP");
			server.start();
			if (startJabox) {
				while (System.in.available() == 0) {
					Thread.sleep(5000);
				}
				server.stop();
				server.join();

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(100);
		}
	}

	/**
	 * Helper function to add an embedded Server using the className to the
	 * running Jetty Server.
	 * 
	 * @param server
	 *            The Jetty server.
	 * @param className
	 *            The className of the EmbeddedServer.
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	private static void addEmbeddedServer(final Server server,
			final String className) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException {
		EmbeddedServer es = (EmbeddedServer) Class.forName(className)
				.newInstance();
		es.addWebAppContext(server);
	}
}
