package org.jabox.model;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

public class HttpPostDeployerEditor extends Panel {
	private static final long serialVersionUID = 8894309704559729007L;

	public HttpPostDeployerEditor(String id, IModel<Configuration> model) {
		super(id, new CompoundPropertyModel<String>(model));
		add(new TextField<String>("url").setRequired(true));
	}
}
