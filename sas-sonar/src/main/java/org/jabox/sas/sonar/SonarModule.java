package org.jabox.sas.sonar;

import org.jabox.apis.cqm.CQMConnector;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.multibindings.Multibinder;

public class SonarModule implements Module {

	@Override
	public void configure(Binder binder) {
		Multibinder<CQMConnector> uriBinder = Multibinder.newSetBinder(binder,
				CQMConnector.class);
		uriBinder.addBinding().to(SonarConnector.class);
	}
}
