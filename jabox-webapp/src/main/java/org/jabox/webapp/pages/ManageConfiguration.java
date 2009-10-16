package org.jabox.webapp.pages;

import java.util.List;

import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.persistence.provider.GeneralDao;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.jabox.apis.Connector;
import org.jabox.apis.Manager;
import org.jabox.apis.bts.BTSConnector;
import org.jabox.apis.cis.CISConnector;
import org.jabox.apis.rms.RMSConnector;
import org.jabox.apis.scm.SCMConnector;
import org.jabox.model.Configuration;
import org.jabox.webapp.borders.MiddlePanel;

/**
 * Homepage
 */
@AuthorizeInstantiation("ADMIN")
public class ManageConfiguration extends MiddlePanel {

	private static final long serialVersionUID = 1L;

	@SpringBean
	protected GeneralDao _generalDao;

	@SpringBean
	protected Manager<Connector> _manager;

	public ManageConfiguration() {
		final Configuration configuration = _generalDao.getConfiguration();
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		add(feedbackPanel);
		Form<Configuration> form = new Form<Configuration>("form") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {
				info("Saving Configuration: " + configuration);
				_generalDao.persist(configuration);
			}
		};
		CompoundPropertyModel<Configuration> model = new CompoundPropertyModel<Configuration>(
				configuration);
		form.setModel(model);
		form.add(new RequiredTextField<Configuration>("issueManagementUrl"));
		// DeployerPluginSelector child = new DeployerPluginSelector(
		// "configuration", model);
		// form.add(child);

		// DeployerPlugin plugin = registry.getEntry((String) pluginId);
		// configuration.setDeployerConfig(plugin
		// .newConfig());
		//
		// child.replace(plugin.newEditor("editor",
		// new PropertyModel(configuration, "deployerConfig")));
		SCMConnector.class.getSimpleName();
		add(configuration, form, SCMConnector.class);
		add(configuration, form, BTSConnector.class);
		add(configuration, form, CISConnector.class);
		add(configuration, form, RMSConnector.class);
		add(form);
	}

	@Override
	protected void onBeforeRender() {
		super.onBeforeRender();
	}

	private void add(final Configuration configuration,
			final Form<Configuration> form, Class<? extends Connector> connector) {
		List<Connector> connectors = _manager.getConnectors(connector);
		System.out.println("connectors: " + connector.getName() + ":"
				+ connectors);

		DropDownChoice<Connector> ddc = new DropDownChoice<Connector>(connector
				.getSimpleName(), new PropertyModel<Connector>(configuration,
				connector.getSimpleName()), connectors,
				new ChoiceRenderer<Connector>("toString", "toString"));
		form.add(ddc);
	}
}
