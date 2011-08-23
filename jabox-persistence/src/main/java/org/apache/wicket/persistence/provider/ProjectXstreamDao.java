package org.apache.wicket.persistence.provider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jabox.environment.Environment;
import org.jabox.model.Project;
import org.jabox.model.User;

import com.thoughtworks.xstream.XStream;

public class ProjectXstreamDao {

	private static XStream getXStream() {
		XStream xstream = new XStream();
		xstream.alias("project", Project.class);
		return xstream;
	}

	public static void persist(Project project) {
		XStream xstream = getXStream();
		String xml = xstream.toXML(project);
		try {
			File projectsDir = Environment.getProjectsDir();
			File file = new File(projectsDir, project.getName() + ".xml");
			FileWriter writer = new FileWriter(file);
			writer.write(xml);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<Project> getProjects() {
		ArrayList<Project> projects = new ArrayList<Project>();
		File dir = Environment.getProjectsDir();

		String[] children = dir.list();
		if (children == null) {
			// Either dir does not exist or is not a directory
		} else {
			for (int i = 0; i < children.length; i++) {
				// Get filename of file or directory
				String filename = children[i];
				String name = filename.replaceAll(".xml$", "");
				projects.add(getProject(name));
			}
		}

		return projects;
	}

	public static Project getProject(String name) {
		XStream xstream = getXStream();

		File dir = Environment.getProjectsDir();
		File file = new File(dir, name + ".xml");

		try {
			FileInputStream is = new FileInputStream(file);
			Project project = (Project) xstream.fromXML(is);
			return project;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void deleteProject(Project project) {
		File file = new File(Environment.getProjectsDir(), project.getName()
				+ ".xml");
		file.delete();
	}
}
