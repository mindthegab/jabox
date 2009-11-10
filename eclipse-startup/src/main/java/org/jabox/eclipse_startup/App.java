/*
 * The MIT License
 *
 * Copyright (c) 2009 Dimitris Kapanidis
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
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
