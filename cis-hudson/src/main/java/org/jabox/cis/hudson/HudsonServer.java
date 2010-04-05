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

import org.jabox.apis.embedded.AbstractEmbeddedServer;
import org.jabox.maven.helper.MavenDownloader;

public class HudsonServer extends AbstractEmbeddedServer {

	private static final String GROUP_ID = "org.jvnet.hudson.main";
	private static final String ARTIFACT_ID = "hudson-war";
	private static final String VERSION = "1.352";
	private static final String TYPE = "war";

	@Override
	public String getServerName() {
		return "hudson";
	}

	@Override
	public String getWarPath() {
		return MavenDownloader.downloadArtifact(GROUP_ID, ARTIFACT_ID, VERSION,
				TYPE).getAbsolutePath();
	}
}
