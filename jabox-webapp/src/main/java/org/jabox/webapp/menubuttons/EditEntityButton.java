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
package org.jabox.webapp.menubuttons;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.form.ImageButton;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.persistence.domain.BaseEntityDetachableModel;
import org.apache.wicket.persistence.provider.GeneralDao;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.jabox.apis.Connector;
import org.jabox.model.DeployersRegistry;
import org.jabox.model.Server;
import org.jabox.webapp.modifiers.TooltipModifier;
import org.jabox.webapp.pages.EditServerPage;
import org.jabox.webapp.pages.ManageServers;

public final class EditEntityButton<T extends Server> extends ImageButton {

	private static final TooltipModifier TOOLTIP_MODIFIER = new TooltipModifier(
			"Edit Connector");

	private static final ResourceReference EDIT_IMG = new ResourceReference(
			EditEntityButton.class, "preferences-system.png");

	private static final long serialVersionUID = 1L;
	private final T _item;

	public EditEntityButton(final String id, final T item) {
		super(id, EDIT_IMG);
		_item = item;
		add(TOOLTIP_MODIFIER);
	}

	public EditEntityButton(final String id, final ListItem<T> item) {
		this(id, item.getModelObject());
	}

	@SpringBean(name = "GeneralDao")
	protected GeneralDao _generalDao;

	@SpringBean
	private DeployersRegistry registry;

	/**
	 * Delete from persistent storage, commit transaction.
	 */
	@Override
	public void onSubmit() {
		IModel<Server> model = new BaseEntityDetachableModel<Server>(_item);

		Connector connector = registry.getEntry(model.getObject()
				.getDeployerConfig().pluginId);
		setResponsePage(new EditServerPage(new CompoundPropertyModel<Server>(
				model), connector.getClass()) {

			@Override
			protected void onCancel() {
				setResponsePage(ManageServers.class);
			}

			@Override
			protected void onSave(Server server) {
				_generalDao.persist(server);
				setResponsePage(ManageServers.class);
			}
		});
	}
}
