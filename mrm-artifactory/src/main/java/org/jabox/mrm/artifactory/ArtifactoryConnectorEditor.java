package org.jabox.mrm.artifactory;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.jabox.model.Server;

public class ArtifactoryConnectorEditor extends Panel {
	private static final long serialVersionUID = 8151822895020720306L;

	public ArtifactoryConnectorEditor(String id, IModel<Server> model) {
		super(id, new CompoundPropertyModel<String>(model));
		add(new TextField<String>("server.url"));
	}

}
