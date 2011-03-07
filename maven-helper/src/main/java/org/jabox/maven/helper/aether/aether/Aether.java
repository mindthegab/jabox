package org.jabox.maven.helper.aether.aether;

import java.io.File;
import java.util.List;
import java.util.Vector;

import org.apache.maven.repository.internal.DefaultServiceLocator;
import org.apache.maven.repository.internal.MavenRepositorySystemSession;
import org.sonatype.aether.RepositorySystem;
import org.sonatype.aether.RepositorySystemSession;
import org.sonatype.aether.collection.CollectRequest;
import org.sonatype.aether.collection.DependencyCollectionException;
import org.sonatype.aether.connector.wagon.WagonProvider;
import org.sonatype.aether.connector.wagon.WagonRepositoryConnectorFactory;
import org.sonatype.aether.graph.Dependency;
import org.sonatype.aether.repository.LocalRepository;
import org.sonatype.aether.repository.RemoteRepository;
import org.sonatype.aether.resolution.ArtifactRequest;
import org.sonatype.aether.resolution.ArtifactResolutionException;
import org.sonatype.aether.resolution.ArtifactResult;
import org.sonatype.aether.spi.connector.RepositoryConnectorFactory;
import org.sonatype.aether.util.artifact.DefaultArtifact;

public class Aether {
	private String remoteRepository;
	private RepositorySystem repositorySystem;
	private LocalRepository localRepository;

	public Aether(String remoteRepository, String localRepository) {
		this.remoteRepository = remoteRepository;
		this.repositorySystem = newSystem();
		this.localRepository = new LocalRepository(localRepository);
	}

	private RepositorySystem newSystem() {
		DefaultServiceLocator locator = new DefaultServiceLocator();
		locator.setServices(WagonProvider.class, new ManualWagonProvider());
		locator.addService(RepositoryConnectorFactory.class,
				WagonRepositoryConnectorFactory.class);
		return locator.getService(RepositorySystem.class);
	}

	private RepositorySystemSession newSession() {
		MavenRepositorySystemSession session = new MavenRepositorySystemSession();
		session.setLocalRepositoryManager(repositorySystem
				.newLocalRepositoryManager(localRepository));
		return session;
	}

	public File resolveArtifact(String groupId, String artifactId,
			String version, String extension)
			throws DependencyCollectionException, ArtifactResolutionException {
		RepositorySystemSession session = newSession();
		DefaultArtifact artifact = new DefaultArtifact(groupId, artifactId, "",
				extension, version);
		Dependency dependency = new Dependency(artifact, "runtime");
		RemoteRepository central = new RemoteRepository("central", "default",
				remoteRepository);

		CollectRequest collectRequest = new CollectRequest();
		collectRequest.setRoot(dependency);
		collectRequest.addRepository(central);

		List<RemoteRepository> repositories = new Vector<RemoteRepository>();
		repositories.add(central);
		ArtifactRequest ar = new ArtifactRequest(artifact, repositories, null);
		ArtifactResult result = repositorySystem.resolveArtifact(session, ar);

		return result.getArtifact().getFile();
	}
}
