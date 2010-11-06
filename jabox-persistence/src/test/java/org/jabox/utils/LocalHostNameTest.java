package org.jabox.utils;

import junit.framework.TestCase;

public class LocalHostNameTest extends TestCase {

	public void testGetLocalHostname() {
		String localHostname = LocalHostName.getLocalHostname();
		System.out.println(localHostname);
	}
}
