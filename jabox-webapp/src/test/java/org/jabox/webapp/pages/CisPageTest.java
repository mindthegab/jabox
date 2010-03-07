package org.jabox.webapp.pages;

import junit.framework.TestCase;

import org.apache.wicket.spring.injection.annot.test.AnnotApplicationContextMock;
import org.apache.wicket.util.tester.WicketTester;
import org.jabox.application.TestWicketApplication;

public class CisPageTest extends TestCase {

	private WicketTester tester;
	private AnnotApplicationContextMock mockContext;

	@Override
	public void setUp() {
		tester = new WicketTester(new TestWicketApplication());
		mockContext = ((TestWicketApplication) tester.getApplication())
				.getMockContext();
	}

	public void testMyPageBasicRender0() {
		tester.startPage(CisPage.class);
		tester.assertRenderedPage(CisPage.class);
	}

}
