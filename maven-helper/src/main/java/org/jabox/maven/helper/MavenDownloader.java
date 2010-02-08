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
package org.jabox.maven.helper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.factory.ArtifactFactory;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.artifact.repository.ArtifactRepositoryPolicy;
import org.apache.maven.artifact.repository.DefaultArtifactRepositoryFactory;
import org.apache.maven.artifact.repository.layout.DefaultRepositoryLayout;
import org.apache.maven.artifact.resolver.ArtifactNotFoundException;
import org.apache.maven.artifact.resolver.ArtifactResolutionException;
import org.apache.maven.artifact.resolver.ArtifactResolver;
import org.codehaus.classworlds.ClassWorld;
import org.codehaus.plexus.PlexusContainerException;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;
import org.codehaus.plexus.embed.Embedder;

public class MavenDownloader {

	private static final String MAVEN_REPO = "http://repo1.maven.org/maven2/";

	private static ArtifactRepositoryPolicy ARTIFACT_REPOSITORY_POLICY = new ArtifactRepositoryPolicy(
			true, ArtifactRepositoryPolicy.UPDATE_POLICY_NEVER,
			ArtifactRepositoryPolicy.CHECKSUM_POLICY_WARN);

	private static ArtifactRepository ARTIFACT_REPOSITORY = new DefaultArtifactRepositoryFactory()
			.createArtifactRepository("local", MAVEN_REPO,
					new DefaultRepositoryLayout(), ARTIFACT_REPOSITORY_POLICY,
					ARTIFACT_REPOSITORY_POLICY);;

	/**
	 * @param groupId
	 * @param artifactId
	 * @param version
	 * @param type
	 */
	public static File downloadArtifact(final String groupId,
			final String artifactId, final String version, final String type) {

		try {
			return retrieveArtifact(groupId, artifactId, version, type);
		} catch (ArtifactResolutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ArtifactNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static File getArtifactFile(String groupId, String artifactId,
			String version, String type) {
		String userHome = System.getProperty("user.home");
		assert (userHome != null);
		StringBuffer sb = new StringBuffer(userHome);
		sb.append(File.separator);
		sb.append(".m2");
		sb.append(File.separator);
		sb.append("repository");
		sb.append(File.separator);
		sb.append(groupId.replace('.', File.separatorChar));
		sb.append(File.separator);
		sb.append(artifactId);
		sb.append(File.separator);
		sb.append(version);
		sb.append(File.separator);
		sb.append(artifactId);
		sb.append("-");
		sb.append(version);
		sb.append(".");
		sb.append(type);
		File file = new File(sb.toString());
		assert (file.exists());
		return file;
	}

	private static File retrieveArtifact(final String groupId,
			final String artifactId, final String version,
			final String packaging) throws ArtifactResolutionException,
			ArtifactNotFoundException {
		try {
			Embedder embedder = new Embedder();
			ClassWorld classWorld = new ClassWorld();

			try {
				embedder.start(classWorld);
			} catch (PlexusContainerException e) {
				throw new RuntimeException(
						"Unable to start the embedded plexus container", e);
			}

			Artifact artifact = ((ArtifactFactory) embedder
					.lookup(ArtifactFactory.ROLE)).createBuildArtifact(groupId,
					artifactId, version, packaging);

			ArtifactResolver artifactResolver = (ArtifactResolver) embedder
					.lookup(ArtifactResolver.ROLE);
			List<ArtifactRepository> remoteRepositories = new ArrayList<ArtifactRepository>();
			remoteRepositories.add(ARTIFACT_REPOSITORY);
			artifactResolver.resolve(artifact, remoteRepositories,
					ARTIFACT_REPOSITORY);
			return artifact.getFile();
		} catch (ComponentLookupException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
