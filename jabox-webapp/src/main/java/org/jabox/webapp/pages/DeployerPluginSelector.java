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
import org.jabox.apis.Connector;
import org.jabox.model.Configuration;
import org.jabox.model.DeployerConfig;
import org.jabox.model.DeployerPlugin;
import org.jabox.model.DeployersRegistry;
import org.jabox.model.Server;

public class DeployerPluginSelector extends Panel {
	private static final long serialVersionUID = -222526477140616108L;
	@SpringBean
	private DeployersRegistry registry;

	public DeployerPluginSelector(String id, final IModel<Server> article) {
		super(id);
		add(new WebMarkupContainer("editor"));
		String pluginId = article.getObject().deployerConfig != null ? ((Server) article
				.getObject()).deployerConfig.pluginId
				: "-1";
		if (article.getObject().deployerConfig != null) {
			Connector plugin = registry.getEntry(pluginId);
			DeployerPluginSelector.this.replace(plugin
					.newEditor("editor", new PropertyModel<Server>(
							article, "deployerConfig")));

		}

		add(new PluginPicker("picker",
				new CompoundPropertyModel<Configuration>(pluginId)) {
			private static final long serialVersionUID = -5528219523437017579L;

			@Override
			protected void onSelectionChanged(Object pluginId) {
				Connector plugin = registry.getEntry((String) pluginId);
				Server configuration = article.getObject();
				DeployerConfig newConfig = plugin.newConfig();
				configuration.setDeployerConfig(newConfig);

				DeployerPluginSelector.this.replace(plugin.newEditor("editor",
						new PropertyModel(article, "deployerConfig")));
			}
		});

		// DeployerPlugin plugin = registry.getEntry("plugin.deployer.ftp");
		// Configuration configuration = (Configuration) article
		// .getObject();
		// DeployerConfig newConfig = plugin.newConfig();
		// configuration.setDeployerConfig(newConfig);

		// DeployerPluginSelector.this.replace(plugin.newEditor("editor",
		// new PropertyModel(article, "deployerConfig")));

	}

	private static abstract class PluginPicker<T> extends DropDownChoice<T> {
		private static final long serialVersionUID = 1346317031364661388L;
		@SpringBean
		private DeployersRegistry registry;

		public PluginPicker(String id, IModel<T> model) {
			super(id);
			setRequired(true);
			setModel(model);
			setChoices(new LoadableDetachableModel() {
				private static final long serialVersionUID = 6694323103247193118L;

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
