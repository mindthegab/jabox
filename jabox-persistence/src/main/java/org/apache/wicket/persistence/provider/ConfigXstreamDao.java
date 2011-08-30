package org.apache.wicket.persistence.provider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.jabox.environment.Environment;
import org.jabox.model.DefaultConfiguration;

import com.thoughtworks.xstream.XStream;

public class ConfigXstreamDao {

	private static XStream getXStream() {
		XStream xstream = new XStream();
		xstream.alias("config", DefaultConfiguration.class);
		return xstream;
	}

	public static void persist(DefaultConfiguration config) {
		XStream xstream = getXStream();
		String xml = xstream.toXML(config);
		try {
			File dir = Environment.getBaseDirFile();
			File file = new File(dir, "config.xml");
			FileWriter writer = new FileWriter(file);
			writer.write(xml);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static DefaultConfiguration getConfig() {
		XStream xstream = getXStream();

		File dir = Environment.getBaseDirFile();
		File file = new File(dir, "config.xml");

		try {
			FileInputStream is = new FileInputStream(file);
			DefaultConfiguration config = (DefaultConfiguration) xstream
					.fromXML(is);
			return config;
		} catch (FileNotFoundException e) {
			DefaultConfiguration config = new DefaultConfiguration();
			ConfigXstreamDao.persist(config);
			return config;
		}
	}
}
