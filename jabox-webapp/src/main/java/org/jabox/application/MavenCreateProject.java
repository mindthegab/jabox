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
import java.io.IOException;

import org.apache.maven.MavenExecutionException;
import org.apache.maven.artifact.InvalidRepositoryException;
import org.apache.maven.cli.MavenCli;
import org.jabox.model.MavenArchetype;
import org.jabox.model.Project;

public class MavenCreateProject {

	/**
	 * 
	 * @param project
	 * @param baseDir
	 *            the base directory where to add the project files.
	 * @return
	 * @throws IOException
	 * @throws InvalidRepositoryException
	 * @throws MavenExecutionException
	 */
	public static String createProjectWithMavenCore(final Project project,
			final String baseDir) throws IOException,
			InvalidRepositoryException, MavenExecutionException {

		MavenArchetype ma = project.getMavenArchetype();
		String[] args = new String[] { "archetype:generate",
				"-DarchetypeGroupId=" + ma.getArchetypeGroupId(),
				"-DarchetypeArtifactId=" + ma.getArchetypeArtifactId(),
				"-DarchetypeVersion=" + ma.getArchetypeVersion(),
				"-DgroupId=org.jabox", "-Dversion=1.0.0-SNAPSHOT",
				"-Dpackage=org.jabox", "-DartifactId=" + project.getName(),
				"-DinteractiveMode=false" };
		MavenCli cli = new MavenCli();
		cli.doMain(args, baseDir, System.out, System.err);

		return baseDir + File.separatorChar + project.getName();
	}
}
