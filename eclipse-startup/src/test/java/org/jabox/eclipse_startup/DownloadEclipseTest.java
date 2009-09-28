package org.jabox.eclipse_startup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import junit.framework.TestCase;

import org.jabox.utils.DownloadHelper;
import org.xml.sax.SAXException;

public abstract class DownloadEclipseTest extends TestCase {

	public void testDownloadFile() throws MalformedURLException,
			FileNotFoundException, IOException, SAXException {
		EclipseRunner er = EclipseRunner.getInstance();
		DownloadHelper.downloadFile(er.getDownloadURL(), new File(
				"target/eclipse"));
	}
}
