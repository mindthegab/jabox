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
