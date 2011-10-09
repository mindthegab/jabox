package org.jabox.mrm.nexus;

import org.jabox.apis.rms.RMSConnector;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.multibindings.Multibinder;

public class NexusModule implements Module {

	@Override
	public void configure(Binder binder) {
		Multibinder<RMSConnector> uriBinder = Multibinder.newSetBinder(binder,
				RMSConnector.class);
		uriBinder.addBinding().to(NexusConnector.class);
	}
}
