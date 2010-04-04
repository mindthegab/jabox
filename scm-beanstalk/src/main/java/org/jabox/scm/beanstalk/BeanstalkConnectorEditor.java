package org.jabox.scm.beanstalk;

import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.jabox.model.Server;

public class BeanstalkConnectorEditor extends Panel {
	private static final long serialVersionUID = -4137475647749541936L;

	public BeanstalkConnectorEditor(final String id, final IModel<Server> model) {
		super(id, new CompoundPropertyModel<String>(model));
		add(new TextField<String>("server.url", new BeanstalkAccountURLModel(
				new PropertyModel<String>(model, "server.url"))));
		add(new TextField<String>("username").setRequired(true));
		add(new TextField<String>("projectName").setRequired(true));
		add(new PasswordTextField("password").setRequired(true));
	}
}
