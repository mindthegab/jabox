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

public class EclipseRunnerWindows extends EclipseRunner {

	public String getDownloadURL() {
		return "http://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/galileo/R/eclipse-jee-galileo-win32.zip&url=http://ftp.wh2.tu-dresden.de/pub/mirrors/eclipse/technology/epp/downloads/release/galileo/R/eclipse-jee-galileo-win32.zip&mirror_id=324";
	}

	public String getFileName() {
		return "eclipse-jee-galileo-win32.zip";
	}

	public File getEclipseExecutable(final File eclipseHome) {
		String absolutePath = eclipseHome.getAbsolutePath();
		File eclipseDir = new File(absolutePath, "eclipse");
		return new File(eclipseDir, "eclipse.exe");
	}
}
