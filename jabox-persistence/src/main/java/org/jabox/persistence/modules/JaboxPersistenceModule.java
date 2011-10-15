package org.jabox.persistence.modules;

import org.jabox.apis.IManager;
import org.jabox.apis.Manager;
import org.jabox.model.DeployersRegistry;
import org.jabox.model.IDeployersRegistry;

import com.google.inject.Binder;
import com.google.inject.Module;

public class JaboxPersistenceModule implements Module {

	@Override
	public void configure(Binder binder) {
		binder.bind(IManager.class).to(Manager.class);
		binder.bind(IDeployersRegistry.class).to(DeployersRegistry.class);
	}

}
