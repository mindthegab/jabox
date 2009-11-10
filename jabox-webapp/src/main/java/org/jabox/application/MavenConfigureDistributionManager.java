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
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.maven.model.DeploymentRepository;
import org.apache.maven.model.DistributionManagement;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.jabox.apis.rms.RMSConnector;

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
	 * @param rms
	 * @throws IOException
	 * @throws XmlPullParserException
	 */
	public static void injectDistributionManager(final File pomFile,
			RMSConnector rms) throws IOException, XmlPullParserException {
		FileReader fileReader = new FileReader(pomFile);
		Model model = new MavenXpp3Reader().read(fileReader);

		DistributionManagement dm = getDistributionManagement(rms
				.getReleaseRepositoryURL(), rms.getSnapshotsRepositoryURL());
		model.setDistributionManagement(dm);

		FileWriter fileWriter = new FileWriter(pomFile);
		new MavenXpp3Writer().write(fileWriter, model);
	}

	private static DistributionManagement getDistributionManagement(
			String releasesRepo, String snapshotsRepo) {
		DistributionManagement distManagement = new DistributionManagement();

		DeploymentRepository snap = new DeploymentRepository();
		snap.setId("snapshots");
		snap.setName("Snapshots");
		snap.setUrl(snapshotsRepo);

		distManagement.setSnapshotRepository(snap);

		DeploymentRepository repo = new DeploymentRepository();
		repo.setId("releases");
		repo.setName("Releases");
		repo.setUrl(releasesRepo);

		distManagement.setRepository(repo);

		return distManagement;
	}
}
