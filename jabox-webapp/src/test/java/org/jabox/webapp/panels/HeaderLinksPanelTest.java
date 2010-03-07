package org.jabox.webapp.panels;

import junit.framework.TestCase;

import org.apache.wicket.spring.injection.annot.test.AnnotApplicationContextMock;
import org.apache.wicket.util.tester.WicketTester;
import org.jabox.application.TestWicketApplication;
import org.jabox.webapp.pages.CisPage;

public class HeaderLinksPanelTest extends TestCase {

	private WicketTester tester;
	private AnnotApplicationContextMock mockContext;

	@Override
	public void setUp() {
		tester = new WicketTester(new TestWicketApplication());
		mockContext = ((TestWicketApplication) tester.getApplication())
				.getMockContext();
	}

	public void testMyPageBasicRender() {
		// mockContext.putBean(new GeneralDaoImpl());
		tester.startPage(CisPage.class);
		// tester.assertRenderedPage(CisPage.class);
	}

}
