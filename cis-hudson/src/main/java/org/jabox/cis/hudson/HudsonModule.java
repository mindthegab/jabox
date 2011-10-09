package org.jabox.cis.hudson;

import org.jabox.apis.cis.CISConnector;
import org.jabox.cis.ehudson.EHudsonConnector;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.multibindings.Multibinder;

public class HudsonModule implements Module {

	@Override
	public void configure(Binder binder) {
		Multibinder<CISConnector> uriBinder = Multibinder.newSetBinder(binder,
				CISConnector.class);
		uriBinder.addBinding().to(HudsonConnector.class);
		uriBinder.addBinding().to(EHudsonConnector.class);

	}
}
