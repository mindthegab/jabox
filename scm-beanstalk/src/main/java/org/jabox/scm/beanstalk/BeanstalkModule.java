package org.jabox.scm.beanstalk;

import org.jabox.apis.scm.SCMConnector;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.multibindings.Multibinder;

public class BeanstalkModule implements Module {

	@Override
	public void configure(Binder binder) {
		Multibinder<SCMConnector> uriBinder = Multibinder.newSetBinder(binder,
				SCMConnector.class);
		uriBinder.addBinding().to(BeanstalkConnector.class);
	}
}
