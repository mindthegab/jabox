package org.jabox.scm.git;

import java.io.File;

import junit.framework.TestCase;

public class GITFacadeTest extends TestCase {

	public void testValidate() {
		fail("Not yet implemented");
	}

	public void testCheckoutBaseDir() {
		IGITConnectorConfig svnc = new GITConnectorConfig();

		File storePath = new File("target/foo");
		storePath.mkdirs();
		new GITFacade().checkoutBaseDir(storePath, svnc);
	}

	public void testCommitProject() {
		fail("Not yet implemented");
	}

}
