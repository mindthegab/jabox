package org.apache.wicket.persistence.provider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jabox.environment.Environment;
import org.jabox.model.Container;

import com.thoughtworks.xstream.XStream;

public class ContainerXstreamDao {

	private static XStream getXStream() {
		XStream xstream = new XStream();
		xstream.alias("container", Container.class);
		return xstream;
	}

	public static void persist(Container container) {
		XStream xstream = getXStream();
		String xml = xstream.toXML(container);
		try {
			File containerDir = Environment.getContainersDir();
			File file = new File(containerDir, container.getName() + ".xml");
			FileWriter writer = new FileWriter(file);
			writer.write(xml);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<Container> getContainers() {
		ArrayList<Container> containers = new ArrayList<Container>();
		File containersDir = Environment.getContainersDir();

		String[] children = containersDir.list();
		if (children == null) {
			// Either dir does not exist or is not a directory
		} else {
			for (int i = 0; i < children.length; i++) {
				// Get filename of file or directory
				String filename = children[i];
				String name = filename.replaceAll(".xml$", "");
				containers.add(getContainer(name));
			}
		}

		return containers;
	}

	public static Container getContainer(String name) {
		XStream xstream = getXStream();

		File containersDir = Environment.getContainersDir();
		File file = new File(containersDir, name + ".xml");

		try {
			FileInputStream is = new FileInputStream(file);
			Container container = (Container) xstream.fromXML(is);
			return container;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void deleteContainer(Container container) {
		File file = new File(Environment.getContainersDir(), container.getName() + ".xml");
		file.delete();
	}
}
