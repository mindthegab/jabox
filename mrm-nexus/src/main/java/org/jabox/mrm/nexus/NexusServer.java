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
package org.jabox.mrm.nexus;

import java.io.File;

import org.jabox.apis.embedded.AbstractEmbeddedServer;
import org.jabox.environment.Environment;
import org.jabox.utils.DownloadHelper;

/**
 * 
 */
public class NexusServer extends AbstractEmbeddedServer {
	final String URL = "http://nexus.sonatype.org/downloads/nexus-webapp-1.5.0.war";

	public static void main(String[] args) throws Exception {
		new NexusServer().startServerAndWait();
	}

	@Override
	public String getServerName() {
		return "nexus";
	}

	@Override
	public String getWarPath() {
		String baseDir = Environment.getBaseDir();

		// Download the artifactory.war
		File zipFile = new File(baseDir, "tmp/nexus.war");
		if (!zipFile.exists()) {
			DownloadHelper.downloadFile(URL, zipFile);
		}
		return zipFile.getAbsolutePath();

		// return MavenDownloader.downloadArtifact("org.sonatype.nexus",
		// "nexus-webapp", "1.3.6", "war").getAbsolutePath();
	}
}
