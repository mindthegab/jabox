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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.ice.tar.TarEntry;
import com.ice.tar.TarInputStream;

public class UntarEclipse {
	public static void untar(final InputStream in, final String untarDir)
			throws IOException {

		System.out.println("Reading TarInputStream... ");
		TarInputStream tin = new TarInputStream(in);
		TarEntry tarEntry = tin.getNextEntry();
		if (new File(untarDir).exists()) {
			while (tarEntry != null) {
				File destPath = new File(untarDir + File.separatorChar
						+ tarEntry.getName());
				System.out.println("Processing " + destPath.getAbsoluteFile());
				if (!tarEntry.isDirectory()) {
					FileOutputStream fout = new FileOutputStream(destPath);
					tin.copyEntryContents(fout);
					fout.close();
				} else {
					destPath.mkdir();
				}
				tarEntry = tin.getNextEntry();
			}
			tin.close();
		} else {
			System.out.println("That destination directory doesn't exist! "
					+ untarDir);
		}

	}

}
