package org.jabox.model;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

public class HttpPostDeployerEditor extends Panel
{
    public HttpPostDeployerEditor(String id, IModel model)
    {
        super(id, new CompoundPropertyModel(model));
        add(new TextField("url").setRequired(true));
    }
}
