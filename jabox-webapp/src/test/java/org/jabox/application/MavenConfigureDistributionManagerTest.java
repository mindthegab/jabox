package org.jabox.application;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

public class MavenConfigureDistributionManagerTest extends TestCase {

	public void testMe() throws URISyntaxException, IOException,
			XmlPullParserException {
		URL resource = MavenConfigureDistributionManager.class
				.getResource("testPom.xml");
		File pomFile = new File(resource.toURI());
		Assert.assertTrue(pomFile.exists());
		// MavenConfigureDistributionManager.injectDistributionManager(pomFile,
		// null);
	}

}
