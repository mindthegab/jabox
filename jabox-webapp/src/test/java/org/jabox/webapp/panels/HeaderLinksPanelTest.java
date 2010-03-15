package org.jabox.webapp.panels;

import junit.framework.TestCase;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.test.AnnotApplicationContextMock;
import org.apache.wicket.util.tester.TestPanelSource;
import org.apache.wicket.util.tester.WicketTester;
import org.jabox.application.DummyWicketApplication;

public class HeaderLinksPanelTest extends TestCase {

	private WicketTester _tester;
	private AnnotApplicationContextMock _mockContext;

	@Override
	public void setUp() {
		_tester = new WicketTester(new DummyWicketApplication());
		_mockContext = ((DummyWicketApplication) _tester.getApplication())
				.getMockContext();
	}

	public void testMyPageBasicRender0() {
		_tester.startPanel(new TestPanelSource() {
			private static final long serialVersionUID = 1L;

			public Panel getTestPanel(final String panelId) {
				// Mock Data
				int selected = HeaderLinksPanel.ALM;

				return new HeaderLinksPanel(panelId, selected);
			}
		});
	}

	public void testMyPageBasicRender1() {
		_tester.startPanel(new TestPanelSource() {
			private static final long serialVersionUID = 1L;

			public Panel getTestPanel(final String panelId) {
				// Mock Data
				int selected = HeaderLinksPanel.ITS;

				return new HeaderLinksPanel(panelId, selected);
			}
		});
	}

	public AnnotApplicationContextMock getMockContext() {
		return _mockContext;
	}

}
