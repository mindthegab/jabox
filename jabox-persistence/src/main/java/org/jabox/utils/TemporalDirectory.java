package org.jabox.utils;

import java.io.File;
import java.io.IOException;

import org.jabox.environment.Environment;

public class TemporalDirectory {

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public static File createTempDir() throws IOException {
		File dir = new File(Environment.getBaseDir(), "tmp");

		if (!dir.exists()) {
			dir.mkdirs();
		}

		File tempDirectory = File.createTempFile("temp", "", dir);
		tempDirectory.delete();
		tempDirectory.mkdir();
		return tempDirectory;
	}
}
