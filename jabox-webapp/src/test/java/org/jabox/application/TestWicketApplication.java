package org.jabox.application;

import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.spring.injection.annot.test.AnnotApplicationContextMock;
import org.jabox.apis.Connector;
import org.jabox.apis.Manager;
import org.jabox.cis.hudson.HudsonConnector;
import org.jabox.facades.GeneralDaoMock;
import org.jabox.webapp.application.WicketApplication;

public class TestWicketApplication extends WicketApplication {
	private AnnotApplicationContextMock mockContext;

	@Override
	protected void springInjection() {
		// Mock ApplicationContext
		mockContext = new AnnotApplicationContextMock();

		// Mock Manager
		Manager<Connector> manager = new Manager<Connector>();
		manager._connectors = new Connector[] { new HudsonConnector() };

		// Mock GeneralDao
		mockContext.putBean(new GeneralDaoMock());
		mockContext.putBean(manager);

		// Inject to Spring
		addComponentInstantiationListener(new SpringComponentInjector(this,
				mockContext, false));
	}

	public AnnotApplicationContextMock getMockContext() {
		return mockContext;
	}
}
