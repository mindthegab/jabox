/*
 * The MIT License
 *
 * Copyright (c) 2009 Dimitris Kapanidis
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.jabox.webapp.pages;

import java.util.List;

import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.persistence.provider.GeneralDao;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.jabox.apis.Connector;
import org.jabox.apis.Manager;
import org.jabox.apis.scm.SCMConnector;
import org.jabox.model.DeployerPlugin;
import org.jabox.model.DeployersRegistry;
import org.jabox.model.Project;
import org.jabox.model.Server;
import org.jabox.webapp.borders.MiddlePanel;

@AuthorizeInstantiation("ADMIN")
public class CreateServer extends MiddlePanel {

	@SpringBean(name = "GeneralDao")
	protected GeneralDao generalDao;

	@SpringBean
	protected Manager<Connector> _manager;

	@SpringBean
	private DeployersRegistry registry;

	public CreateServer() {
		final Server server = new Server();
		// XXX
		// server.setDeployerConfig(DeployernewConfig);

		// Add a FeedbackPanel for displaying our messages
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		add(feedbackPanel);

		// Add a form with an onSumbit implementation that sets a message
		Form<Server> form = new Form<Server>("form") {
			private static final long serialVersionUID = -662744155604166387L;

			protected void onSubmit() {
				// We need to persist twice because the id is necessary for the
				// creation of the project.
				generalDao.persist(server);
				info("Server \"" + server.getName() + "\" Created.");
			}
		};
		CompoundPropertyModel<Server> model = new CompoundPropertyModel<Server>(
				server);
		form.setModel(model);
		add(form);

		form.add(new RequiredTextField<Project>("name"));

		Class<? extends Connector> connector = SCMConnector.class;
		List<Connector> connectors = _manager.getConnectors(connector);
		System.out.println("connectors: " + ":" + connectors);

		DeployerPluginSelector child = new DeployerPluginSelector(
				"configuration", model);
		form.add(child);

		DeployerPlugin plugin = registry.getEntries().get(0);
		server.setDeployerConfig(plugin.newConfig());

		child.replace(plugin.newEditor("editor", new PropertyModel(
				server, "deployerConfig")));

//		DropDownChoice<Connector> ddc = new DropDownChoice<Connector>(
//				"connectorType", new PropertyModel<Connector>(server,
//						"mavenArchetype"), connectors,
//				new ChoiceRenderer<Connector>("toString", "toString"));
//		form.add(ddc);

	}
}
