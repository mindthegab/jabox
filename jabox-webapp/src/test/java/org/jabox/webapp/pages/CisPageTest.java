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
package org.jabox.webapp.pages;

import junit.framework.TestCase;

import org.apache.wicket.spring.injection.annot.test.AnnotApplicationContextMock;
import org.apache.wicket.util.tester.WicketTester;
import org.jabox.application.DummyWicketApplication;

public class CisPageTest extends TestCase {

	private WicketTester _tester;
	private AnnotApplicationContextMock _mockContext;

	@Override
	public void setUp() {
		_tester = new WicketTester(new DummyWicketApplication());
		_mockContext = ((DummyWicketApplication) _tester.getApplication())
				.getMockContext();
	}

	public void testMyPageBasicRender0() {
		_tester.startPage(CisPage.class);
		_tester.assertRenderedPage(CisPage.class);
	}

	public AnnotApplicationContextMock getMockContext() {
		return _mockContext;
	}

}
