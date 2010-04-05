/*
 * Jabox Open Source Version
 * Copyright (C) 2009-2010 Dimitris Kapanidis                                                                                                                          
 * 
 * This file is part of Jabox
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
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
