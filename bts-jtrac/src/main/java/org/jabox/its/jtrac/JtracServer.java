/*
 * Jabox Open Source Version
 * Copyright (C) 2009-2010 Dimitris Kapanidis                                                                                                                          
 * 
 * This file is part of Jabox
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 */
package org.jabox.its.jtrac;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.jabox.apis.embedded.AbstractEmbeddedServer;
import org.jabox.environment.Environment;
import org.jabox.utils.DownloadHelper;
import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.bio.SocketConnector;
import org.mortbay.jetty.webapp.WebAppContext;

public class JtracServer extends AbstractEmbeddedServer {
	private static final String URL = "http://sourceforge.net/projects/j-trac/files/jtrac/2.0/jtrac-2.0.zip/download";

	public static void main(final String[] args) {
		JtracServer jtracServer = new JtracServer();
		startJtracWar(new File(jtracServer.getWarPath()));
	}

	@Override
	protected boolean getParentLoaderPriority() {
		return true;
	}

	private static void startJtracWar(final File jtracWar) {
		Server server = new Server();
		SocketConnector connector = new SocketConnector();
		// Set some timeout options to make debugging easier.
		connector.setMaxIdleTime(1000 * 60 * 60);
		connector.setSoLingerTime(-1);
		connector.setPort(9091);
		server.setConnectors(new Connector[] { connector });

		WebAppContext bb = new WebAppContext();
		bb.setServer(server);
		bb.setContextPath("/");
		String absolutePath = jtracWar.getAbsolutePath();
		bb.setWar(absolutePath);
		bb.setParentLoaderPriority(true);
		// START JMX SERVER
		// MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
		// MBeanContainer mBeanContainer = new MBeanContainer(mBeanServer);
		// server.getContainer().addEventListener(mBeanContainer);
		// mBeanContainer.start();

		server.addHandler(bb);
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

	public static File retrieveJtracWar(final File jtracZip) {
		try {
			ZipFile zip = new ZipFile(jtracZip);
			ZipEntry entry = zip.getEntry("jtrac/jtrac.war");
			InputStream is = zip.getInputStream(entry);
			BufferedInputStream bin = null;
			BufferedOutputStream bout = null;
			bin = new BufferedInputStream(is);
			File jtracWar = File.createTempFile("jtrac", ".war");
			jtracWar.deleteOnExit();
			bout = new BufferedOutputStream(new FileOutputStream(jtracWar));
			while (true) {
				int datum = bin.read();
				if (datum == -1) {
					break;
				}
				bout.write(datum);
			}
			bout.flush();
			return jtracWar;
		} catch (ZipException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void copy(final InputStream in, final OutputStream out)
			throws IOException {

		BufferedInputStream bin = new BufferedInputStream(in);
		BufferedOutputStream bout = new BufferedOutputStream(out);

		while (true) {
			int datum = bin.read();
			if (datum == -1) {
				break;
			}
			bout.write(datum);
		}
		bout.flush();
	}

	@Override
	public String getServerName() {
		return "jtrac";
	}

	@Override
	public String getWarPath() {
		String baseDir = Environment.getBaseDir();

		File zipFile = new File(baseDir, "tmp/jtrac.zip");
		if (!zipFile.exists()) {
			DownloadHelper.downloadFile(URL, zipFile);
		}
		File jtracWar = retrieveJtracWar(zipFile);
		return jtracWar.getAbsolutePath();
	}
}
