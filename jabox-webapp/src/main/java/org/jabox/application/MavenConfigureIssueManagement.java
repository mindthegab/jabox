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

import org.apache.maven.model.IssueManagement;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.jabox.apis.its.ITSConnectorConfig;
import org.jabox.model.Project;

/**
 * Helper class that injects the &lt;issueManagement&gt; configuration to a pom
 * file.
 */
public class MavenConfigureIssueManagement {
	public MavenConfigureIssueManagement() {
	}

	/**
	 * Injects the &lt;issueManagement&gt; configuration to the pom file.
	 * 
	 * @param pomFile
	 *            the pom file that will be injected with the issueManagement
	 *            configuration.
	 * @param its
	 * @param project
	 * @throws IOException
	 * @throws XmlPullParserException
	 */
	public static void injectIssueManagement(final File pomFile,
			final ITSConnectorConfig its, Project project) throws IOException,
			XmlPullParserException {
		FileReader fileReader = new FileReader(pomFile);
		Model model = new MavenXpp3Reader().read(fileReader);

		if (its != null) {
			IssueManagement issueManagement = getIssueManagement(its, project);
			model.setIssueManagement(issueManagement);
		}

		FileWriter fileWriter = new FileWriter(pomFile);
		new MavenXpp3Writer().write(fileWriter, model);
	}

	private static IssueManagement getIssueManagement(ITSConnectorConfig its,
			Project project) {
		IssueManagement issueManagement = new IssueManagement();
		issueManagement.setUrl(its.getProjectUrl(project));
		issueManagement.setSystem(its.getSystem());
		return issueManagement;
	}
}
