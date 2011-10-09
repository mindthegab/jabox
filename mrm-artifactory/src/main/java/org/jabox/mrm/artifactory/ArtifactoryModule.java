package org.jabox.mrm.artifactory;

import org.jabox.apis.rms.RMSConnector;
import org.jabox.mrm.eartifactory.EArtifactoryConnector;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.multibindings.Multibinder;

public class ArtifactoryModule implements Module {

	@Override
	public void configure(Binder binder) {
		Multibinder<RMSConnector> uriBinder = Multibinder.newSetBinder(binder,
				RMSConnector.class);
		uriBinder.addBinding().to(ArtifactoryConnector.class);
		uriBinder.addBinding().to(EArtifactoryConnector.class);
	}
}
