package org.jabox.model;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;

public interface DeployerPlugin extends Identifiable<String> {
	String getName();

	String deploy(Configuration article);

	Component newEditor(String id, IModel<Configuration> model);

	DeployerConfig newConfig();
}
