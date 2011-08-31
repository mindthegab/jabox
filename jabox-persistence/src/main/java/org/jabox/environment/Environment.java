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

	public static String getBaseDir() {
		return getBaseDirFile().getAbsolutePath();
	}

	public static File getBaseDirFile() {
		File dir = new File(getHomeDir());
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return dir;
	}

	public static File getCustomMavenHomeDir() {
		File m2Dir = new File(getBaseDirFile(), CUSTOM_MAVEN_DIR);
		if (!m2Dir.exists()) {
			m2Dir.mkdirs();
		}
		return m2Dir;
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
			return Environment.getBaseDir() + HUDSON_DIR;
		}
	}

	public static File getTmpDirFile() {
		File tmpDir = new File(getBaseDirFile(), "tmp");

		if (!tmpDir.exists()) {
			tmpDir.mkdirs();
		}

		return tmpDir;
	}

	protected static String getHomeDir() {
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
			System.setProperty(property, Environment.getBaseDir()
					+ File.separator + subdir);
		}
	}

	public static File getUsersDir() {
		File dir = new File(getBaseDirFile(), "users");
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return dir;
	}

	public static File getContainersDir() {
		File dir = new File(getBaseDirFile(), "containers");
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return dir;
	}

	public static File getProjectsDir() {
		File dir = new File(getBaseDirFile(), "projects");
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return dir;
	}

	public static File getServersDir() {
		File dir = new File(getBaseDirFile(), "servers");
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return dir;
	}

}
