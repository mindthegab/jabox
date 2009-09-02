package org.jabox.webapp.pages;

import java.io.Serializable;
import java.util.List;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.jabox.model.Configuration;
import org.jabox.model.DeployerConfig;
import org.jabox.model.DeployerPlugin;
import org.jabox.model.DeployersRegistry;

public class DeployerPluginSelector extends Panel {
	@SpringBean
	private DeployersRegistry registry;

	public DeployerPluginSelector(String id, final IModel article) {
		super(id);
		add(new WebMarkupContainer("editor"));
		 String pluginId = ((Configuration) article.getObject()).deployerConfig != null ? ((Configuration) article
				.getObject()).deployerConfig.pluginId : "-1";
		if (((Configuration) article.getObject()).deployerConfig != null) {
			DeployerPlugin plugin = registry.getEntry(pluginId);
			DeployerPluginSelector.this.replace(plugin.newEditor("editor",
					new PropertyModel(article,
							"deployerConfig")));

		}
		
		
		add(new PluginPicker("picker", new CompoundPropertyModel(
				pluginId)) {
			@Override
			protected void onSelectionChanged(Object pluginId) {
				DeployerPlugin plugin = registry.getEntry((String) pluginId);
				Configuration configuration = (Configuration) article
						.getObject();
				DeployerConfig newConfig = plugin.newConfig();
				configuration.setDeployerConfig(newConfig);

				DeployerPluginSelector.this.replace(plugin.newEditor("editor",
						new PropertyModel(article, "deployerConfig")));
			}
		});
		
//		DeployerPlugin plugin = registry.getEntry("plugin.deployer.ftp");
//		Configuration configuration = (Configuration) article
//				.getObject();
//		DeployerConfig newConfig = plugin.newConfig();
//		configuration.setDeployerConfig(newConfig);

//		DeployerPluginSelector.this.replace(plugin.newEditor("editor",
//				new PropertyModel(article, "deployerConfig")));

	}

	private static abstract class PluginPicker extends DropDownChoice {
		@SpringBean
		private DeployersRegistry registry;

		public PluginPicker(String id, IModel model) {
			super(id);
			setRequired(true);
			setModel(model);
			setChoices(new LoadableDetachableModel() {
				@Override
				protected List<? extends String> load() {
					return registry.getIds();
				}
			});

			setChoiceRenderer(new IChoiceRenderer() {
				public Object getDisplayValue(Object object) {
					return registry.getEntry((Serializable) object).getName();
				}

				public String getIdValue(Object object, int index) {
					return (String) object;
				}
			});
		}

		@Override
		protected boolean wantOnSelectionChangedNotifications() {
			return true;
		}

		@Override
		protected abstract void onSelectionChanged(Object pluginId);
	}

}
