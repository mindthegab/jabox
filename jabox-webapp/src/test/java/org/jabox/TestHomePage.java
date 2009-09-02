package org.jabox;

import junit.framework.TestCase;

import org.apache.wicket.util.tester.WicketTester;
import org.jabox.webapp.application.WicketApplication;
import org.jabox.webapp.pages.HomePage;

/**
 * Simple test using the WicketTester
 */
public abstract class TestHomePage extends TestCase
{
	private WicketTester tester;

	public void setUp()
	{
		tester = new WicketTester(new WicketApplication());
	}

	public void testRenderMyPage()
	{
		//start and render the test page
		tester.startPage(HomePage.class);

		//assert rendered page class
		tester.assertRenderedPage(HomePage.class);

		//assert rendered label component
//		tester.assertPageLink("link", "If you see this message wicket is properly configured and running");
	}
}
