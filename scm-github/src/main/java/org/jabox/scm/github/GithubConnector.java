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
package org.jabox.scm.github;

import java.io.File;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.jabox.apis.scm.SCMException;
import org.jabox.model.DeployerConfig;
import org.jabox.model.Project;
import org.jabox.model.Server;
import org.jabox.scm.git.GITConnector;
import org.jabox.scm.git.GITRepository;
import org.jabox.scm.git.IGITConnectorConfig;

public class GithubConnector extends GITConnector {
	public static final String ID = "plugin.scm.github";

	private static final long serialVersionUID = -3875844507330633672L;

	@Override
	public String getName() {
		return "GitHub";
	}

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public String toString() {
		return getName();
	}

	@Override
	public DeployerConfig newConfig() {
		return new GithubConnectorConfig();
	}

	@Override
	public Component newEditor(final String id, final IModel<Server> model) {
		return new GithubConnectorEditor(id, model);
	}

	/**
	 * Creates the Repository of the Project in GitHub and a local repository on
	 * gitRepo/ directory.
	 */
	@Override
	public File createProjectDirectories(final Project project,
			final IGITConnectorConfig config) throws SCMException {
		GitHubFacade.createRepowithApi(config.getUsername(), config
				.getPassword(), project.getName());
		return super.createProjectDirectories(project, config);
	}

	/**
	 * Commits the code on the local Repository and pushes the commit to the
	 * remote origin (GitHub).
	 */
	@Override
	public void commitProject(final Project project,
			final IGITConnectorConfig svnc) throws SCMException {
		super.commitProject(project, svnc);
		File dir = new File(GITRepository.getGitBaseDir(), project.getName());
		dir = new File(dir, project.getName());
		GitHubFacade
				.remoteAddOrigin(svnc.getUsername(), project.getName(), dir);
		GitHubFacade.pushOriginMaster(dir);
	}
}
