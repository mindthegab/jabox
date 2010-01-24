package org.jabox.scm.esvn;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.jabox.model.Server;

public class ESVNConnectorEditor extends Panel {
	private static final long serialVersionUID = -4137475647749541936L;

	public ESVNConnectorEditor(String id, IModel<Server> model) {
		super(id, new CompoundPropertyModel<String>(model));
	}

}
