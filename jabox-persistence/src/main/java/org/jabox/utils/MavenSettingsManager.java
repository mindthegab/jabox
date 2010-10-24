package org.jabox.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.jabox.environment.Environment;

public class MavenSettingsManager {

	public static void writeCustomSettings() {
		File m2Dir = Environment.getCustomMavenHomeDir();
		File file = new File(m2Dir, "settings.xml");
		if (!file.exists()) {
			try {
				writeCustomSettings(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static void writeCustomSettings(final File file) throws IOException {
		URL resource = MavenSettingsManager.class.getResource("settings.xml");
		FileUtils.copyURLToFile(resource, file);
	}

}
