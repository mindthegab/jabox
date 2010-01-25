package org.jabox.its.jtrac;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.jabox.model.Server;

public class JtracRepositoryEditor extends Panel {
	private static final long serialVersionUID = 7888186230733277184L;

	public JtracRepositoryEditor(String id, IModel<Server> model) {
		super(id, new CompoundPropertyModel<String>(model));
		add(new TextField<String>("server.url"));
	}
}
