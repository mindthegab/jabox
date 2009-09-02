package org.jabox.eclipse_startup;

import java.io.File;

public class EclipseRunnerWindows extends EclipseRunner {

	public String getDownloadURL() {
		return "http://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/galileo/R/eclipse-jee-galileo-win32.zip&url=http://ftp.wh2.tu-dresden.de/pub/mirrors/eclipse/technology/epp/downloads/release/galileo/R/eclipse-jee-galileo-win32.zip&mirror_id=324";
	}

	public String getFileName() {
		return "eclipse-jee-galileo-win32.zip";
	}

	public String getEclipseExecutable(final File eclipseHome) {
		String absolutePath = eclipseHome.getAbsolutePath();
		return absolutePath + File.separator + "eclipse.exe";
	}

}
