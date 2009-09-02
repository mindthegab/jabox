package org.jabox.eclipse_startup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import org.jabox.utils.DownloadHelper;
import org.xml.sax.SAXException;

public class DownloadEclipse {

	/**
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws SAXException
	 * @throws FileNotFoundException
	 */
	public static File downloadFile() throws MalformedURLException,
			IOException, SAXException, FileNotFoundException {
		EclipseRunner er = new EclipseRunnerWindows();

		File outputFile = new File("eclipse.zip");
		DownloadHelper.downloadFile(er.getDownloadURL(), outputFile);
		System.out.println("\nFile is created........................");
		return outputFile;
	}

}
