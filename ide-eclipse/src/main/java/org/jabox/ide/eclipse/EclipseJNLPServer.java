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
package org.jabox.ide.eclipse;

import org.jabox.apis.embedded.AbstractEmbeddedServer;
import org.jabox.maven.helper.MavenDownloader;

public class EclipseJNLPServer extends AbstractEmbeddedServer {

	@Override
	public String getServerName() {
		return "eclipse";
	}

	@Override
	public String getWarPath() {
		return MavenDownloader.downloadArtifact("org.jabox", "eclipse-webapp",
				"1.0.0-SNAPSHOT", "war").getAbsolutePath();
	}
}
