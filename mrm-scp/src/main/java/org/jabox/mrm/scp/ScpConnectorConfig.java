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
package org.jabox.mrm.scp;

import org.jabox.apis.rms.RMSConnectorConfig;
import org.jabox.model.DeployerConfig;

public class ScpConnectorConfig extends DeployerConfig implements
		RMSConnectorConfig {
	private static final long serialVersionUID = -891223857972401214L;

	private String absoluteReleaseRepositoryPath;

	private String absoluteSnapshotRepositoryPath;

	public ScpConnectorConfig() {
		pluginId = ScpConnector.ID;
	}

	public String getReleaseRepositoryURL() {
		return "scp://" + getServer().getUrl()
				+ getAbsoluteReleaseRepositoryPath();
	}

	public String getSnapshotsRepositoryURL() {
		return "scp://" + getServer().getUrl()
				+ getAbsoluteSnapshotRepositoryPath();
	}

	public void setAbsoluteReleaseRepositoryPath(
			String absoluteReleaseRepositoryPath) {
		this.absoluteReleaseRepositoryPath = absoluteReleaseRepositoryPath;
	}

	public String getAbsoluteReleaseRepositoryPath() {
		return absoluteReleaseRepositoryPath;
	}

	public void setAbsoluteSnapshotRepositoryPath(
			String absoluteSnapshotRepositoryPath) {
		this.absoluteSnapshotRepositoryPath = absoluteSnapshotRepositoryPath;
	}

	public String getAbsoluteSnapshotRepositoryPath() {
		return absoluteSnapshotRepositoryPath;
	}
}
