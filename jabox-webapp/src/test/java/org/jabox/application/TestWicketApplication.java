package org.jabox.application;

import org.apache.wicket.persistence.provider.GeneralDaoImpl;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.spring.injection.annot.test.AnnotApplicationContextMock;
import org.jabox.webapp.application.WicketApplication;

public class TestWicketApplication extends WicketApplication {
	private AnnotApplicationContextMock mockContext;

	@Override
	protected void springInjection() {
		mockContext = new AnnotApplicationContextMock();
		// mockContext.putBean("EhourConfig", new EhourConfigStub());
		mockContext.putBean("GeneralDao", new GeneralDaoImpl());

		addComponentInstantiationListener(new SpringComponentInjector(this,
				mockContext, false));
	}

	public AnnotApplicationContextMock getMockContext() {
		return mockContext;
	}
}
