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
