package org.jabox.sas.sonar;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.validation.validator.UrlValidator;
import org.jabox.model.Server;

public class SonarConnectorEditor extends Panel {
	private static final long serialVersionUID = 7983760045396158217L;

	public SonarConnectorEditor(final String id, final IModel<Server> model) {
		super(id, new CompoundPropertyModel<Server>(model));
		add(new TextField<String>("server.url").add(new UrlValidator()));
	}
}
