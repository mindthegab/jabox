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

import org.apache.maven.model.Activation;
import org.apache.maven.model.ActivationProperty;
import org.apache.maven.model.BuildBase;
import org.apache.maven.model.Model;
import org.apache.maven.model.Plugin;
import org.apache.maven.model.PluginExecution;
import org.apache.maven.model.Profile;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.jabox.model.Project;

/**
 * Helper class that injects the signing process for the attached artifacts.
 */
public class MavenConfigureSignArtifacts {

	public MavenConfigureSignArtifacts() {
	}

	/**
	 * 
	 * @param pomFile
	 * @param project
	 * @throws IOException
	 * @throws XmlPullParserException
	 */
	public static void injectSignArtifact(final File pomFile, Project project)
			throws IOException, XmlPullParserException {
		FileReader fileReader = new FileReader(pomFile);
		Model model = new MavenXpp3Reader().read(fileReader);

		if (project.isSignArtifactReleases()) {
			model.addProfile(createReleaseSignArtifactsProfile());
		}

		FileWriter fileWriter = new FileWriter(pomFile);
		new MavenXpp3Writer().write(fileWriter, model);
	}

	private static Profile createReleaseSignArtifactsProfile() {
		Profile profile = new Profile();
		profile.setId("release-sign-artifacts");
		profile.setActivation(createActivation());
		profile.setBuild(createBaseBuild());
		return profile;
	}

	private static Activation createActivation() {
		Activation activation = new Activation();
		activation.setProperty(createActivationProperty());
		return activation;
	}

	private static ActivationProperty createActivationProperty() {
		ActivationProperty property = new ActivationProperty();
		property.setName("performRelease");
		property.setValue("true");
		return property;
	}

	private static BuildBase createBaseBuild() {
		BuildBase buildBase = new BuildBase();
		buildBase.addPlugin(createPlugin());
		return buildBase;
	}

	private static Plugin createPlugin() {
		Plugin plugin = new Plugin();
		plugin.setGroupId("org.apache.maven.plugins");
		plugin.setArtifactId("maven-gpg-plugin");
		plugin.setVersion("1.0");
		plugin.addExecution(createPluginExecution());
		return plugin;
	}

	private static PluginExecution createPluginExecution() {
		PluginExecution pluginExecution = new PluginExecution();
		pluginExecution.setId("sign-artifacts");
		pluginExecution.setPhase("verify");
		pluginExecution.addGoal("sign");
		return pluginExecution;
	}
}
