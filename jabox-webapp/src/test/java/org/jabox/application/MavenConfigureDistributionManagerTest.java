/*
 * Jabox Open Source Version
 * Copyright (C) 2009-2010 Dimitris Kapanidis                                                                                                                          
 * 
 * This file is part of Jabox
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 */
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
		URL resource = MavenConfigureDistributionManagement.class
				.getResource("testPom.xml");
		File pomFile = new File(resource.toURI());
		Assert.assertTrue(pomFile.exists());
		// MavenConfigureDistributionManager.injectDistributionManager(pomFile,
		// null);
	}

}
