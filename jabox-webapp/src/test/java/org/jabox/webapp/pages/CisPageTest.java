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
