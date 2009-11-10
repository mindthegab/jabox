/*
 * The MIT License
 *
 * Copyright (c) 2009 Dimitris Kapanidis
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.jabox.application;

import java.io.File;
import java.io.IOException;

import org.apache.maven.artifact.InvalidRepositoryException;
import org.apache.maven.cli.MavenCli;
import org.apache.maven.reactor.MavenExecutionException;
import org.codehaus.classworlds.ClassWorld;
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
	public static String createProjectWithMavenCore(Project project,
			String baseDir) throws IOException, InvalidRepositoryException,
			MavenExecutionException {

		MavenArchetype ma = project.getMavenArchetype();
		String[] args = new String[] {
				"org.apache.maven.plugins:maven-archetype-plugin:2.0-alpha-4:generate",
				"-DarchetypeGroupId=" + ma.getArchetypeGroupId(),
				"-DarchetypeArtifactId=" + ma.getArchetypeArtifactId(),
				"-DarchetypeVersion=" + ma.getArchetypeVersion(),
				"-DgroupId=org.jabox", "-Dversion=1.0.0-SNAPSHOT",
				"-Dpackage=org.jabox", "-Duser.dir=" + baseDir,
				"-DartifactId=" + project.getName(), "-DinteractiveMode=false" };
		ClassWorld classWorld = new ClassWorld();
		MavenCli.main(args, classWorld);

		return baseDir + File.separatorChar + project.getName();
	}
}
