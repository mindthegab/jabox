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
package org.jabox.application;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.maven.model.Model;
import org.apache.maven.model.Scm;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.jabox.apis.scm.SCMConnectorConfig;
import org.jabox.model.Project;

/**
 * Helper class that injects the &lt;scm&gt; configuration to a pom file.
 */
public class MavenConfigureSCM {
	public MavenConfigureSCM() {
	}

	/**
	 * Injects the &lt;scm&gt; configuration to the pom file.
	 * 
	 * @param pomFile
	 *            the pom file that will be injected with the
	 *            distributionManager configuration.
	 * @param scm
	 * @param project
	 * @throws IOException
	 * @throws XmlPullParserException
	 */
	public static void injectScm(final File pomFile,
			final SCMConnectorConfig scm, final Project project)
			throws IOException, XmlPullParserException {
		FileReader fileReader = new FileReader(pomFile);
		Model model = new MavenXpp3Reader().read(fileReader);

		String scmMavenUrl = project.getScmMavenUrl();
		Scm sc = getScm(scmMavenUrl, scmMavenUrl, scm.getServer().getUrl());
		model.setScm(sc);

		FileWriter fileWriter = new FileWriter(pomFile);
		new MavenXpp3Writer().write(fileWriter, model);
	}

	private static Scm getScm(final String connection,
			final String developerConnection, final String url) {
		Scm scm = new Scm();

		scm.setConnection(connection);
		scm.setDeveloperConnection(developerConnection);
		scm.setUrl(url);

		return scm;
	}
}
