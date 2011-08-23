package org.apache.wicket.persistence.provider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jabox.apis.ConnectorConfig;
import org.jabox.environment.Environment;
import org.jabox.model.Server;

import com.thoughtworks.xstream.XStream;

public class ServerXstreamDao {

	private static XStream getXStream() {
		XStream xstream = new XStream();
		xstream.alias("server", Server.class);
		return xstream;
	}

	public static void persist(ConnectorConfig config) {
		XStream xstream = getXStream();
		String xml = xstream.toXML(config);
		try {
			File dir = Environment.getServersDir();
			File file = new File(dir, config.getServer().getName() + ".xml");
			FileWriter writer = new FileWriter(file);
			writer.write(xml);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<ConnectorConfig> getServers(
			Class<? extends ConnectorConfig> clas) {
		ArrayList<ConnectorConfig> servers = new ArrayList<ConnectorConfig>();
		File dir = Environment.getServersDir();

		String[] children = dir.list();
		if (children == null) {
			// Either dir does not exist or is not a directory
		} else {
			for (int i = 0; i < children.length; i++) {
				// Get filename of file or directory
				String filename = children[i];
				String name = filename.replaceAll(".xml$", "");
				servers.add(getServer(name));
			}
		}

		return servers;
	}

	public static ConnectorConfig getServer(String name) {
		XStream xstream = getXStream();

		File dir = Environment.getServersDir();
		File file = new File(dir, name + ".xml");

		try {
			FileInputStream is = new FileInputStream(file);
			ConnectorConfig server = (ConnectorConfig) xstream.fromXML(is);
			return server;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void deleteServer(Server server) {
		File file = new File(Environment.getServersDir(), server.getName()
				+ ".xml");
		file.delete();
	}
}
