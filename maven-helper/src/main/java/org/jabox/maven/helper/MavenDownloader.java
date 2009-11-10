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
package org.jabox.maven.helper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.maven.cli.MavenCli;
import org.codehaus.classworlds.ClassWorld;

public class MavenDownloader {

	private static String BEFORE_GROUP_ID = "<project><modelVersion>4.0.0</modelVersion><artifactId>x</artifactId><groupId>x</groupId><version>1.0.0-SNAPSHOT</version><dependencies><dependency><groupId>";
	private static String BEFORE_ARTIFACT_ID = "</groupId><artifactId>";
	private static String BEFORE_VERSION = "</artifactId><version>";
	private static String BEFORE_TYPE = "</version><type>";
	private static String END = "</type></dependency></dependencies></project>";

	/**
	 * XXX Improve this without the pom.xml
	 * 
	 * @param groupId
	 * @param artifactId
	 * @param version
	 * @param type
	 */
	public static File downloadArtifact(final String groupId,
			final String artifactId, final String version, final String type) {

		retrieveArtifact(groupId, artifactId, version, type);

		return getArtifactFile(groupId, artifactId, version, type);
	}

	public static File getArtifactFile(String groupId, String artifactId,
			String version, String type) {
		String userHome = System.getProperty("user.home");
		assert (userHome != null);
		StringBuffer sb = new StringBuffer(userHome);
		sb.append(File.separator);
		sb.append(".m2");
		sb.append(File.separator);
		sb.append("repository");
		sb.append(File.separator);
		sb.append(groupId.replace('.', File.separatorChar));
		sb.append(File.separator);
		sb.append(artifactId);
		sb.append(File.separator);
		sb.append(version);
		sb.append(File.separator);
		sb.append(artifactId);
		sb.append("-");
		sb.append(version);
		sb.append(".");
		sb.append(type);
		File file = new File(sb.toString());
		assert (file.exists());
		return file;
	}

	private static void retrieveArtifact(final String groupId,
			final String artifactId, final String version, final String type) {
		try {
			// Create a temporal pom.xml"
			File tempDir = File.createTempFile("tempDir", null);
			tempDir.createNewFile();
			File pomXml = tempDir;
			pomXml.createNewFile();

			FileWriter fileWriter = new FileWriter(pomXml);
			fileWriter.write(BEFORE_GROUP_ID);
			fileWriter.append(groupId);
			fileWriter.append(BEFORE_ARTIFACT_ID);
			fileWriter.append(artifactId);
			fileWriter.append(BEFORE_VERSION);
			fileWriter.append(version);
			fileWriter.append(BEFORE_TYPE);
			fileWriter.append(type);
			fileWriter.append(END);
			fileWriter.close();

			String[] args = new String[] { "test", "-f",
					pomXml.getAbsolutePath() };
			ClassWorld classWorld = new ClassWorld();
			MavenCli.main(args, classWorld);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
