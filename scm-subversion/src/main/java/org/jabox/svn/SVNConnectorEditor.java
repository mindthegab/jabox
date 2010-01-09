package org.jabox.svn;

import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.jabox.model.Server;

public class SVNConnectorEditor extends Panel {
	private static final long serialVersionUID = -4137475647749541936L;

	public SVNConnectorEditor(String id, IModel<Server> model) {
		super(id, new CompoundPropertyModel<String>(model));
		add(new TextField<String>("repositoryURL").setRequired(true));
		add(new TextField<String>("username").setRequired(true));
		add(new PasswordTextField("password").setRequired(true));
		add(new CheckBox("embedded"));
	}

}
