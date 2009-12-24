package org.jabox.embedded.jetty;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jabox.environment.Environment;

public class WebappManager {

	public static List<String> getWebapps() throws IOException {
		String baseDir = Environment.getBaseDir();
		File file = new File(baseDir, "servers.csv");
		if (!file.exists()) {
			createDefaultServersFile(file);
		}
		List<String> servers = readData(file);
		return servers;
	}

	/**
	 * Reads the data from the file and converts them to List.
	 * 
	 * @param file
	 * @return a List of the data of the file.
	 * @throws FileNotFoundException
	 */
	private static List<String> readData(File file)
			throws FileNotFoundException {
		List<String> list = new ArrayList<String>();
		Scanner scanner = new Scanner(file);
		while (scanner.hasNextLine()) {
			list.add(scanner.nextLine());
		}
		return list;
	}

	/**
	 * Writes the data from the List to the file.
	 * 
	 * @param file
	 * @param list
	 * @throws IOException
	 */
	private static void writeData(List<String> list, File file)
			throws IOException {
		FileWriter fileWriter = new FileWriter(file);
		for (String str : list) {
			fileWriter.write(str + "\n");
		}
		fileWriter.flush();
		fileWriter.close();
	}

	private static void createDefaultServersFile(File file) throws IOException {
		List<String> list = new ArrayList<String>();
		list.add("org.jabox.cis.hudson.HudsonServer");
		// list.add("org.jabox.mrm.nexus.NexusServer");
		// list.add("org.jabox.ide.eclipse.EclipseJNLPServer");
		list.add("org.jabox.mrm.artifactory.ArtifactoryServer");
		writeData(list, file);
	}
}
