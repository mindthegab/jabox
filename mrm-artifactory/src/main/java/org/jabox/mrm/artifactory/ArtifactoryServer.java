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
package org.jabox.mrm.artifactory;

import java.io.File;

import org.jabox.apis.embedded.AbstractEmbeddedServer;
import org.jabox.environment.Environment;
import org.jabox.utils.DownloadHelper;

/**
 * 
 */
public class ArtifactoryServer extends AbstractEmbeddedServer {

	private static final String URL = "http://sourceforge.net/projects/artifactory/files/artifactory/2.0/artifactory-2.0.8.war/download";

	public static void main(String[] args) throws Exception {
		new ArtifactoryServer().startServerAndWait();
	}

	public String getServerName() {
		return "artifactory";
	}

	public String getWarPath() {
		String baseDir = Environment.getBaseDir();

		// Download the artifactory.war
		File zipFile = new File(baseDir, "tmp/artifactory.war");
		if (!zipFile.exists()) {
			DownloadHelper.downloadFile(URL, zipFile);
		}
		return zipFile.getAbsolutePath();
	}
}
