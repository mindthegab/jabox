package org.jabox.model;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

public class FtpDeployerEditor extends Panel
{
	private static final long serialVersionUID = -2526046655726538823L;

	public FtpDeployerEditor(String id, IModel<Configuration> model)
    {
        super(id, new CompoundPropertyModel<String>(model));
        add(new TextField<String>("host").setRequired(true));
        add(new TextField<String>("port").setRequired(true));
    }

}
