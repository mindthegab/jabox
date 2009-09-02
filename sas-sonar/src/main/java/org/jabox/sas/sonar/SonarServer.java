package org.jabox.sas.sonar;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.bio.SocketConnector;
import org.mortbay.jetty.webapp.WebAppContext;

/**
 * Hello world!
 * 
 */
public class SonarServer {
	public static void main(String[] args) throws Exception {
		// System.setProperty("HUDSON_HOME", "c://");
		// Main.run(new String[]{"-DHUDSON_HOME=c:"});
		// new Main().run(args);

		Server server = new Server();
		SocketConnector connector = new SocketConnector();
		// Set some timeout options to make debugging easier.
		connector.setMaxIdleTime(1000 * 60 * 60);
		connector.setSoLingerTime(-1);
		connector.setPort(9092);
		server.setConnectors(new Connector[] { connector });

		WebAppContext bb = new WebAppContext();
		bb.setServer(server);
		bb.setContextPath("/");
		bb
				.setWar("E:/Documents and Settings/Administrator/.m2/repository/org/codehaus/sonar/sonar-web/1.4.2/sonar-web-1.4.2.war");
		bb.setParentLoaderPriority(true);
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
