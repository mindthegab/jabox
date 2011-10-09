package org.jabox.scm.svn;

import org.jabox.apis.scm.SCMConnector;
import org.jabox.scm.esvn.ESVNConnector;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.multibindings.Multibinder;

public class SVNModule implements Module {

	@Override
	public void configure(Binder binder) {
		Multibinder<SCMConnector> uriBinder = Multibinder.newSetBinder(binder,
				SCMConnector.class);
		uriBinder.addBinding().to(SVNConnector.class);
		uriBinder.addBinding().to(ESVNConnector.class);
	}
}
