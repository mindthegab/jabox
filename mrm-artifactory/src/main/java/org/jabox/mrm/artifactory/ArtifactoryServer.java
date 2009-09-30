package org.jabox.mrm.artifactory;

import java.io.File;

import org.jabox.apis.embedded.AbstractEmbeddedServer;
import org.jabox.environment.Environment;
import org.jabox.utils.DownloadHelper;

/**
 * 
 */
public class ArtifactoryServer extends AbstractEmbeddedServer {

	private static final String URL = "http://sourceforge.net/projects/artifactory/files/artifactory/2.0/artifactory-2.0.8.war/download";

	public static void main(String[] args) throws Exception {
		new ArtifactoryServer().startServerAndWait();
	}

	public String getServerName() {
		return "artifactory";
	}

	public String getWarPath() {
		String baseDir = Environment.getBaseDir();

		// Download the artifactory.war
		File zipFile = new File(baseDir, "tmp/artifactory.war");
		if (!zipFile.exists()) {
			DownloadHelper.downloadFile(URL, zipFile);
		}
		return zipFile.getAbsolutePath();
	}
}
