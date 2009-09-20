package org.jabox.eclipse_startup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import org.jabox.utils.DownloadHelper;
import org.xml.sax.SAXException;

public class DownloadEclipse {

	/**
	 * @param er2
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws SAXException
	 * @throws FileNotFoundException
	 */
	public static File downloadFile(EclipseRunner er, File zipFile)
			throws MalformedURLException, IOException, SAXException,
			FileNotFoundException {
		DownloadHelper.downloadFile(er.getDownloadURL(), zipFile);
		System.out.println("\nFile is created........................");
		return zipFile;
	}

}
