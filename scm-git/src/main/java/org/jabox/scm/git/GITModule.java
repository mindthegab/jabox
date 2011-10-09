package org.jabox.scm.git;

import org.jabox.apis.scm.SCMConnector;
import org.jabox.scm.egit.EGITConnector;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.multibindings.Multibinder;

public class GITModule implements Module {

	@Override
	public void configure(Binder binder) {
		Multibinder<SCMConnector> uriBinder = Multibinder.newSetBinder(binder,
				SCMConnector.class);
		uriBinder.addBinding().to(GITConnector.class);
		uriBinder.addBinding().to(EGITConnector.class);
	}
}
