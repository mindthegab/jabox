/*
 * Jabox Open Source Version
 * Copyright (C) 2009-2010 Dimitris Kapanidis                                                                                                                          
 * 
 * This file is part of Jabox
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 */
package org.jabox.webapp.pages.server;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.jabox.apis.Connector;
import org.jabox.model.Project;
import org.jabox.model.Server;
import org.jabox.webapp.pages.BasePage;
import org.jabox.webapp.pages.DeployerPluginSelector;

public abstract class EditServerPage extends BasePage {

	public EditServerPage(final IModel<Server> server,
			final Class<? extends Connector> connectorClass) {
		add(new FeedbackPanel("feedback"));
		Form<Server> form = new Form<Server>("form",
				new CompoundPropertyModel<Server>(server.getObject())) {
			private static final long serialVersionUID = -8262391690705860769L;

			@Override
			protected void onSubmit() {
				onSave(getModelObject());
			}
		};

		add(form);

		// form.add(new TextField<String>("title"));
		// form.add(new TextArea<String>("content"));
		// form.add(new DeployerPluginSelector("deployer", server));
		// XXX
		// server.setDeployerConfig(DeployernewConfig);

		CompoundPropertyModel<Server> model = new CompoundPropertyModel<Server>(
				server);
		// form.setModel(model);
		// add(form);

		form.add(new RequiredTextField<Project>("name"));

		DeployerPluginSelector child = new DeployerPluginSelector(
				"configuration", model, connectorClass);
		form.add(child);

		// Connector plugin = registry.getEntries().get(0);
		// server.getObject().setDeployerConfig(plugin.newConfig());

		// child.replace(plugin.newEditor("editor", new PropertyModel(
		// server, "deployerConfig")));

		// DropDownChoice<Connector> ddc = new DropDownChoice<Connector>(
		// "connectorType", new PropertyModel<Connector>(server,
		// "mavenArchetype"), connectors,
		// new ChoiceRenderer<Connector>("toString", "toString"));
		// form.add(ddc);

		form.add(new Link<Void>("cancel") {
			private static final long serialVersionUID = -6975617962156076540L;

			@Override
			public void onClick() {
				onCancel();
			}
		});
	}

	protected abstract void onSave(Server article);

	protected abstract void onCancel();
}
