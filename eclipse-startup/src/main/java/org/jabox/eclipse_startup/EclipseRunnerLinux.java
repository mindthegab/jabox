package org.jabox.eclipse_startup;

import java.io.File;

public class EclipseRunnerLinux extends EclipseRunner {

	String getDownloadURL() {
		return "http://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/galileo/R/eclipse-jee-galileo-linux-gtk.tar.gz&url=http://ftp.ing.umu.se/mirror/eclipse/technology/epp/downloads/release/galileo/R/eclipse-jee-galileo-linux-gtk.tar.gz&mirror_id=494";
	}

	String getFileName() {
		return "eclipse-jee-galileo-linux-gtk.tar.gz";
	}

	String getEclipseExecutable(File eclipseHome) {
		return null;
	}

}
