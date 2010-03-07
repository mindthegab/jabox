package org.jabox.webapp.panels;

import junit.framework.TestCase;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.test.AnnotApplicationContextMock;
import org.apache.wicket.util.tester.TestPanelSource;
import org.apache.wicket.util.tester.WicketTester;
import org.jabox.application.TestWicketApplication;

public class HeaderLinksPanelTest extends TestCase {

	private WicketTester tester;
	private AnnotApplicationContextMock mockContext;

	@Override
	public void setUp() {
		tester = new WicketTester(new TestWicketApplication());
		mockContext = ((TestWicketApplication) tester.getApplication())
				.getMockContext();
	}

	public void testMyPageBasicRender0() {
		tester.startPanel(new TestPanelSource() {
			private static final long serialVersionUID = 1L;

			public Panel getTestPanel(String panelId) {
				// Mock Data
				int selected = HeaderLinksPanel.ALM;

				return new HeaderLinksPanel(panelId, selected);
			}
		});
	}

	public void testMyPageBasicRender1() {
		tester.startPanel(new TestPanelSource() {
			private static final long serialVersionUID = 1L;

			public Panel getTestPanel(String panelId) {
				// Mock Data
				int selected = HeaderLinksPanel.ITS;

				return new HeaderLinksPanel(panelId, selected);
			}
		});
	}

}
