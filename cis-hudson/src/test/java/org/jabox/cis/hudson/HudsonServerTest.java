package org.jabox.cis.hudson;

import junit.framework.TestCase;

public class HudsonServerTest extends TestCase {

	public void testStripVersion() {
		String name = HudsonServer.stripVersion("collector-server-1.1.14.hpi");
		assertEquals("collector-server.hpi", name);
	}
}
