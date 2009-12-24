package org.jabox.embedded.jetty;

import java.io.IOException;
import java.util.List;

import junit.framework.TestCase;

public class WebappManagerTest extends TestCase {

	public void testGetWebapps() throws IOException {
		List<String> servers = WebappManager.getWebapps();
		assertEquals("Servers size", 2, servers.size());
	}
}
