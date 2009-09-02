package org.jabox.eclipse_startup;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import org.xml.sax.SAXException;

/**
 * Hello world!
 */
public class App {
	public static void main(final String[] args) throws MalformedURLException,
			IOException, SAXException {
		// File f = DownloadEclipse.downloadFile();
		// File f = new File("eclipse-jee-galileo-linux-gtk.tar.gz");
		EclipseRunner er = EclipseRunner.getInstance();
		File f = new File(er.getFileName());
		File eclipseHome = new File(
				"D:/Documents/My Developments/Jabox/workspace/eclipse-startup/eclipse");
		eclipseHome.mkdirs();
		// UntarEclipse.untar(new FileInputStream(f), dir.getAbsolutePath());
		// UnzipEclipse.unzip(f, dir.getAbsolutePath());
		er.executeEclipse(eclipseHome);
	}
}