package org.jabox.model;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

public class FtpDeployerEditor extends Panel
{
    public FtpDeployerEditor(String id, IModel model)
    {
        super(id, new CompoundPropertyModel(model));
        add(new TextField("host").setRequired(true));
        add(new TextField("port").setRequired(true));
    }

}
