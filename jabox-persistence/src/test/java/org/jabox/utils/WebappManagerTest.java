package org.jabox.utils;

import java.io.IOException;
import java.util.List;

import org.jabox.utils.WebappManager;

import junit.framework.TestCase;

public class WebappManagerTest extends TestCase {

	public void testGetWebapps() throws IOException {
		List<String> servers = WebappManager.getWebapps();
		assertEquals("Servers size", 2, servers.size());
	}
}
