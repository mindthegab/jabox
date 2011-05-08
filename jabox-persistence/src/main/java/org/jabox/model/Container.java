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
package org.jabox.model;

import java.io.File;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.persistence.Entity;

import org.apache.wicket.persistence.domain.BaseEntity;
import org.codehaus.cargo.container.ContainerType;
import org.codehaus.cargo.container.InstalledLocalContainer;
import org.codehaus.cargo.container.configuration.ConfigurationType;
import org.codehaus.cargo.container.configuration.LocalConfiguration;
import org.codehaus.cargo.container.deployable.WAR;
import org.codehaus.cargo.container.installer.Installer;
import org.codehaus.cargo.container.installer.ZipURLInstaller;
import org.codehaus.cargo.generic.DefaultContainerFactory;
import org.codehaus.cargo.generic.configuration.DefaultConfigurationFactory;
import org.jabox.apis.embedded.EmbeddedServer;
import org.jabox.environment.Environment;
import org.jabox.utils.MavenSettingsManager;
import org.jabox.utils.WebappManager;

/**
 * A Project.
 * 
 * @author dimitris
 */
@Entity
public class Container extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String _name;
	private String _port;

	@Override
	public String toString() {
		return "Container: " + _name;
	}

	public void setName(final String name) {
		_name = name;
	}

	public String getName() {
		return _name;
	}

	public void setPort(String _port) {
		this._port = _port;
	}

	public String getPort() {
		return _port;
	}

	public void start() {
		Environment.configureEnvironmentVariables();
		
		// (1) Optional step to install the container from a URL pointing to its
		// distribution
		Installer installer;
		try {
			installer = new ZipURLInstaller(new URL(
					"http://apache.rediris.es/tomcat/tomcat-6/v6.0.32/bin/"
							+ getTomcatFilename()), new File(Environment
					.getBaseDir(), "cargo/installs").getAbsolutePath());
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		installer.install();

		// (2) Create the Cargo Container instance wrapping our physical
		// container
		LocalConfiguration configuration = (LocalConfiguration) new DefaultConfigurationFactory()
				.createConfiguration("tomcat6x", ContainerType.INSTALLED,
						ConfigurationType.STANDALONE, new File(Environment
								.getBaseDir(), "cargo/conf").getAbsolutePath());
		InstalledLocalContainer container = (InstalledLocalContainer) new DefaultContainerFactory()
				.createContainer("tomcat6x", ContainerType.INSTALLED,
						configuration);
		container.setHome(installer.getHome());
		container.setOutput(new File(Environment.getBaseDir(),
				"cargo/cargo.out").getAbsolutePath());
		container.setSystemProperties(System.getProperties());

		MavenSettingsManager.writeCustomSettings();
		try {
			List<String> webapps = WebappManager.getWebapps();
			for (String webapp : webapps) {
				addEmbeddedServer(configuration, webapp);
			}

			System.out
					.println(">>> STARTING EMBEDDED JETTY SERVER, PRESS ANY KEY TO STOP");

			// (4) Start the container
			container.setTimeout(1200000);
			container.start();
			System.out.println(">>> Container started.");

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(100);
		}

	}

	/**
	 * @return the filename of apache tomcat. Depends on the OS.
	 */
	private static String getTomcatFilename() {
		if (Environment.isWindowsPlatform()) {
			return "apache-tomcat-6.0.32.zip";
		}
		return "apache-tomcat-6.0.32.tar.gz";
	}

	/**
	 * Helper function to add an embedded Server using the className to the
	 * running Jetty Server.
	 * 
	 * @param configuration
	 *            The Jetty server.
	 * @param className
	 *            The className of the EmbeddedServer.
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	private static void addEmbeddedServer(
			final LocalConfiguration configuration, final String className)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		EmbeddedServer es = (EmbeddedServer) Class.forName(className)
				.newInstance();
		WAR war = new WAR(es.getWarPath());
		war.setContext(es.getServerName());
		configuration.addDeployable(war);
	}

}
