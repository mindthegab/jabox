package org.jabox.mrm.nexus;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.jabox.model.Server;

public class NexusConnectorEditor extends Panel {
	private static final long serialVersionUID = 7851270123746015638L;

	public NexusConnectorEditor(String id, IModel<Server> model) {
		super(id, new CompoundPropertyModel<String>(model));
		add(new TextField<String>("server.url"));
	}

}
