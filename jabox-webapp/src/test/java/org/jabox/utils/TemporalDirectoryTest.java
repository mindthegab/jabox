package org.jabox.utils;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

public class TemporalDirectoryTest extends TestCase {

	public void testCreateTempDir() throws IOException {
		File tempDir = TemporalDirectory.createTempDir();
		assertTrue(tempDir.exists());
	}

}
