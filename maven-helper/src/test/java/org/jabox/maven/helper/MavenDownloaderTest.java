package org.jabox.maven.helper;

import junit.framework.TestCase;

public class MavenDownloaderTest extends TestCase {

	public void testDownloadArtifact() {
		MavenDownloader.downloadArtifact("org.jvnet.hudson.main", "hudson-war",
				"1.322", "war");
	}

	public void testGetArtifactFile() {
		MavenDownloader.getArtifactFile("org.jvnet.hudson.main", "hudson-war",
				"1.322", "war");
	}
}
