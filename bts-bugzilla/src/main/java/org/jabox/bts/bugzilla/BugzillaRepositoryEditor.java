package org.jabox.bts.bugzilla;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.jabox.model.Server;

public class BugzillaRepositoryEditor extends Panel {
	private static final long serialVersionUID = 7983760045396158217L;

	public BugzillaRepositoryEditor(String id, IModel<Server> model) {
		super(id, new CompoundPropertyModel<String>(model));
		add(new TextField<String>("server.url"));
	}
}
