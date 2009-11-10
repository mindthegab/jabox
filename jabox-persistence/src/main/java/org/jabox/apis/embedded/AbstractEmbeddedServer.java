/*
 * The MIT License
 *
 * Copyright (c) 2009 Dimitris Kapanidis
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
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
		wac.setParentLoaderPriority(getParentLoaderPriority());
		File tempDir = new File(Environment.getBaseDir(), "server-"
				+ getServerName());
		tempDir.mkdirs();
		wac.setTempDirectory(tempDir);
		server.addHandler(wac);
	}

	/**
	 * 
	 * @return true if parentLoaderPriority should be enabled.
	 */
	protected boolean getParentLoaderPriority() {
		return false;
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
