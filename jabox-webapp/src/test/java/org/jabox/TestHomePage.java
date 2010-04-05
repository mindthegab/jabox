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
package org.jabox;

import junit.framework.TestCase;

import org.apache.wicket.util.tester.WicketTester;
import org.jabox.webapp.application.WicketApplication;
import org.jabox.webapp.pages.HomePage;

/**
 * Simple test using the WicketTester
 */
public abstract class TestHomePage extends TestCase {
	private WicketTester tester;

	@Override
	public void setUp() {
		tester = new WicketTester(new WicketApplication());
	}

	public void testRenderMyPage() {
		// start and render the test page
		tester.startPage(HomePage.class);

		// assert rendered page class
		tester.assertRenderedPage(HomePage.class);

		// assert rendered label component
		// tester.assertPageLink("link",
		// "If you see this message wicket is properly configured and running");
	}
}
