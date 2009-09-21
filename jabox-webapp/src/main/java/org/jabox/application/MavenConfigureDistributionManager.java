package org.jabox.application;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.maven.model.DeploymentRepository;
import org.apache.maven.model.DistributionManagement;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

/**
 * Helper class that injects the &lt;distributionManager&gt; configuration to a
 * pom file.
 */
public class MavenConfigureDistributionManager {
	public MavenConfigureDistributionManager() {
	}

	/**
	 * Injects the &lt;distributionManager&gt; configuration to the pom file.
	 * 
	 * @param pomFile
	 *            the pom file that will be injected with the
	 *            distributionManager configuration.
	 * @throws IOException
	 * @throws XmlPullParserException
	 */
	public static void injectDistributionManager(final File pomFile)
			throws IOException, XmlPullParserException {
		FileReader fileReader = new FileReader(pomFile);
		Model model = new MavenXpp3Reader().read(fileReader);

		model.setDistributionManagement(getDistributionManagement());
		FileWriter fileWriter = new FileWriter(pomFile);
		new MavenXpp3Writer().write(fileWriter, model);
	}

	private static DistributionManagement getDistributionManagement() {
		DistributionManagement distManagement = new DistributionManagement();

		DeploymentRepository snapshotRepo = new DeploymentRepository();
		snapshotRepo.setId("snapshots");
		snapshotRepo.setName("Snapshots");
		snapshotRepo
				.setUrl("http://localhost:9090/nexus/content/repositories/snapshots/");
		distManagement.setSnapshotRepository(snapshotRepo);

		DeploymentRepository repo = new DeploymentRepository();
		repo.setId("releases");
		repo.setName("Releases");
		repo
				.setUrl("http://localhost:9090/nexus/content/repositories/releases/");
		distManagement.setRepository(repo);

		return distManagement;
	}
}
