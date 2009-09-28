package org.jabox.eclipse_startup;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import org.jabox.utils.DownloadHelper;
import org.xml.sax.SAXException;

/**
 * Hello world!
 */
public class App {
	public static void main(final String[] args) throws MalformedURLException,
			IOException, SAXException {
		EclipseRunner er = EclipseRunner.getInstance();
		String baseDir = Environment.getBaseDir();

		// Download the eclipse.zip
		File zipFile = new File(baseDir, "tmp/eclipse.zip");
		if (!zipFile.exists()) {
			// File f = new File("eclipse-jee-galileo-linux-gtk.tar.gz");
			// File f = new File(er.getFileName());
			DownloadHelper.downloadFile(er.getDownloadURL(), zipFile);
		}

		// Unpack the Eclipse
		File eclipseHome = new File(baseDir, "eclipse");
		if (!er.getEclipseExecutable(eclipseHome).exists()) {
			UnzipEclipse.unzip(zipFile, eclipseHome);
			// UntarEclipse.untar(new FileInputStream(f),
			// dir.getAbsolutePath());
		}

		// Execute Eclipse
		er.executeEclipse(eclipseHome);
	}
}