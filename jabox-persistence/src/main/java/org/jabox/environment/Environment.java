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
package org.jabox.environment;

import java.io.File;

public class Environment {
	// Used to identify the windows platform.
	private static final String WIN_ID = "Windows";

	private static final String JABOX_ENV = "JABOX_HOME";
	private static final String JABOX_PROPERTY = "JABOX_HOME";
	private static final String HUDSON_ENV = "HUDSON_HOME";
	private static final String HUDSON_PROPERTY = "HUDSON_HOME";
	private static final String HUDSON_DIR = ".hudson";
	private static final String CUSTOM_MAVEN_DIR = ".m2";

	public static File getCustomMavenHomeDir() {
		return createAndReturnDir(new File(getBaseDirFile(), CUSTOM_MAVEN_DIR));
	}

	/**
	 * Try to determine whether this application is running under Windows or
	 * some other platform by examing the "os.name" property.
	 * 
	 * @return true if this application is running under a Windows OS
	 */
	public static boolean isWindowsPlatform() {
		String os = System.getProperty("os.name");
		if (os != null && os.startsWith(WIN_ID)) {
			return true;
		} else {
			return false;
		}
	}

	public static String getHudsonHomeDir() {
		String env = System.getenv(HUDSON_ENV);
		String property = System.getProperty(HUDSON_PROPERTY);
		if (env != null) {
			return env;
		} else if (property != null) {
			return property;
		} else {
			return Environment.getDataDir() + HUDSON_DIR;
		}
	}

	private static String getHomeDir() {
		String env = System.getenv(JABOX_ENV);
		String property = System.getProperty(JABOX_PROPERTY);
		if (env != null) {
			return new File(env).getAbsolutePath() + File.separator;
		} else if (property != null) {
			return new File(property).getAbsolutePath() + File.separator;
		}
		String homeDir = new File(System.getProperty("user.home"), ".jabox")
				.getAbsolutePath()
				+ File.separator;
		System.setProperty(JABOX_PROPERTY, homeDir);
		return homeDir;
	}

	public static void configureEnvironmentVariables() {
		configBaseDir(HUDSON_ENV, HUDSON_PROPERTY, HUDSON_DIR);
		configBaseDir("ARTIFACTORY_HOME", "artifactory.home", ".artifactory/");
		configBaseDir("NEXUS_HOME", "plexus.nexus-work", ".nexus/");
	}

	private static void configBaseDir(final String env, final String property,
			final String subdir) {
		if (System.getenv(env) == null && System.getProperty(property) == null) {
			System.setProperty(property, Environment.getDataDir()
					+ File.separator + subdir);
		}
	}

	public static String getBaseDir() {
		return getBaseDirFile().getAbsolutePath() + File.separator;
	}

	public static File getBaseDirFile() {
		return createAndReturnDir(new File(getHomeDir()));
	}

	public static File getUsersDir() {
		return createAndReturnDir(new File(getConfigDir(), "users"));
	}

	public static File getContainersDir() {
		return createAndReturnDir(new File(getConfigDir(), "containers"));
	}

	public static File getProjectsDir() {
		return createAndReturnDir(new File(getConfigDir(), "projects"));
	}

	public static File getServersDir() {
		return createAndReturnDir(new File(getConfigDir(), "servers"));
	}

	public static File getConfigDir() {
		return createAndReturnDir(new File(getBaseDir(), "config"));
	}

	public static File getTmpDir() {
		return createAndReturnDir(new File(getBaseDirFile(), "tmp"));
	}

	public static File getDownloadsDir() {
		return createAndReturnDir(new File(getBaseDirFile(), "downloads"));
	}
	
	public static File getDataDir() {
		return createAndReturnDir(new File(getBaseDirFile(), "data"));
	}

	private static File createAndReturnDir(File dir) {
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return dir;
	}
}
