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
package org.jabox.cis.hudson;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.codehaus.plexus.util.FileUtils;
import org.jabox.apis.embedded.AbstractEmbeddedServer;
import org.jabox.environment.Environment;
import org.jabox.maven.helper.MavenDownloader;

public class HudsonServer extends AbstractEmbeddedServer {

	private static final String GROUP_ID = "org.jvnet.hudson.main";
	private static final String ARTIFACT_ID = "hudson-war";
	private static final String VERSION = "1.381";
	private static final String TYPE = "war";

	@Override
	public String getServerName() {
		return "hudson";
	}

	@Override
	public String getWarPath() {
		injectPlugins();
		return MavenDownloader.downloadArtifact(GROUP_ID, ARTIFACT_ID, VERSION,
				TYPE).getAbsolutePath();
	}

	public static void injectPlugins() {
		injectPlugin("org.jvnet.hudson.plugins.m2release", "m2release", "0.3.4");
		injectPlugin("org.jvnet.hudson.plugins", "redmine", "0.8");
		injectConfiguration("hudson.tasks.Maven.xml");
	}

	private static void injectConfiguration(String resource) {
		URL res = HudsonServer.class.getResource(resource);
		File dest = new File(Environment.getHudsonHomeDir(), resource);
		if (!dest.exists()) {
			try {
				FileUtils.copyURLToFile(res, dest);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void injectPlugin(String groupId, String artifactId,
			String version) {
		File plugin = MavenDownloader.downloadArtifact(groupId, artifactId,
				version, "hpi");
		try {
			FileUtils.copyFile(plugin, new File(getHudsonPluginDir(),
					stripVersion(plugin)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String stripVersion(final File plugin) {
		String name = plugin.getName();
		name = name.replaceAll("-.*", ".hpi");
		return name;
	}

	private static File getHudsonPluginDir() {
		return new File(Environment.getHudsonHomeDir(), "plugins");
	}
}
