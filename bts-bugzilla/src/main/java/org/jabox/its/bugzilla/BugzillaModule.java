package org.jabox.its.bugzilla;

import org.jabox.apis.its.ITSConnector;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.multibindings.Multibinder;

public class BugzillaModule implements Module {

	@Override
	public void configure(Binder binder) {
		// binder.bind(ITSConnector.class).to(BugzillaRepository.class);
		Multibinder<ITSConnector> uriBinder = Multibinder.newSetBinder(binder,
				ITSConnector.class);
		uriBinder.addBinding().to(BugzillaRepository.class);
	}

}
