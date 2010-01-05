package org.jabox.webapp.pages;

import java.util.List;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.persistence.provider.GeneralDao;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.jabox.apis.Connector;
import org.jabox.apis.Manager;
import org.jabox.apis.scm.SCMConnector;
import org.jabox.model.DeployersRegistry;
import org.jabox.model.Project;
import org.jabox.model.Server;
import org.jabox.webapp.borders.MiddlePanel;
import org.jabox.webapp.utils.TransactionalForm;

public abstract class EditServerPage extends MiddlePanel {

	@SpringBean(name = "GeneralDao")
	protected GeneralDao generalDao;

	@SpringBean
	protected Manager<Connector> _manager;

	@SpringBean
	private DeployersRegistry registry;

	public EditServerPage(IModel<Server> server) {
		add(new FeedbackPanel("feedback"));
		Form<Server> form = new TransactionalForm<Server>("form",
				new CompoundPropertyModel<Server>(server.getObject())) {
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

		Class<? extends Connector> connector = SCMConnector.class;
		List<Connector> connectors = _manager.getConnectors(connector);
		System.out.println("connectors: " + ":" + connectors);

		DeployerPluginSelector child = new DeployerPluginSelector(
				"configuration", model);
		form.add(child);

//		Connector plugin = registry.getEntries().get(0);
//		server.getObject().setDeployerConfig(plugin.newConfig());

		// child.replace(plugin.newEditor("editor", new PropertyModel(
		// server, "deployerConfig")));

		// DropDownChoice<Connector> ddc = new DropDownChoice<Connector>(
		// "connectorType", new PropertyModel<Connector>(server,
		// "mavenArchetype"), connectors,
		// new ChoiceRenderer<Connector>("toString", "toString"));
		// form.add(ddc);

		form.add(new Link<Void>("cancel") {
			@Override
			public void onClick() {
				onCancel();
			}
		});
	}

	protected abstract void onSave(Server article);

	protected abstract void onCancel();
}
