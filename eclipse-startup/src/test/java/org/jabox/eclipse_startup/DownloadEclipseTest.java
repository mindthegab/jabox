package org.jabox.eclipse_startup;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import org.xml.sax.SAXException;

import junit.framework.TestCase;

public abstract class DownloadEclipseTest extends TestCase {

	public void testDownloadFile() throws MalformedURLException, FileNotFoundException, IOException, SAXException {
		DownloadEclipse.downloadFile();
	}

}
