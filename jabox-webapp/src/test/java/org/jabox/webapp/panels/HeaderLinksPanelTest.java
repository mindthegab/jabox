package org.jabox.webapp.panels;

import junit.framework.TestCase;

import org.apache.wicket.util.tester.WicketTester;
import org.jabox.application.TestWicketApplication;
import org.jabox.webapp.pages.CisPage;

public class HeaderLinksPanelTest extends TestCase {

	private WicketTester tester;

	@Override
	public void setUp() {
		tester = new WicketTester();
	}

	public void testMyPageBasicRender() {
		WicketTester tester = new WicketTester();
		// tester.startPage(CisPage.class);
		// tester.assertRenderedPage(CisPage.class);
	}

}
