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
package org.jabox.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class DownloadHelper {
	/**
	 * 
	 * @param urlPath
	 * @return
	 */
	public static File downloadFile(final String urlPath, final File outputFile) {
		InputStream is = null;
		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;

		outputFile.getParentFile().mkdirs();

		try {
			URL url = new URL(urlPath);
			is = url.openStream();
			bin = new BufferedInputStream(is);
			bout = new BufferedOutputStream(new FileOutputStream(outputFile));
			while (true) {
				int datum = bin.read();
				if (datum == -1) {
					break;
				}
				bout.write(datum);
			}
			bout.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				is.close();
				bin.close();
				bout.close();
			} catch (IOException ioe) {
				// nothing to see here
			}
		}
		return outputFile;
	}

}
