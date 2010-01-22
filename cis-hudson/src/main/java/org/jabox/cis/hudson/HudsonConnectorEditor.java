package org.jabox.cis.hudson;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.jabox.model.Server;

public class HudsonConnectorEditor extends Panel {
	private static final long serialVersionUID = -4821476804096973897L;

	public HudsonConnectorEditor(String id, IModel<Server> model) {
		super(id, new CompoundPropertyModel<String>(model));
		add(new TextField<String>("server.url"));
	}

}
