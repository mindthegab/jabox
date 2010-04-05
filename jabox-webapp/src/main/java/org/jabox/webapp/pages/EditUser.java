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
package org.jabox.webapp.pages;

import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.persistence.provider.GeneralDao;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.jabox.apis.Connector;
import org.jabox.apis.Manager;
import org.jabox.model.User;
import org.jabox.webapp.borders.MiddlePanel;

@AuthorizeInstantiation("ADMIN")
public class EditUser extends MiddlePanel {

	private final class EditUserForm extends Form<JaboxAuthenticatedWebSession> {
		private static final long serialVersionUID = 1L;

		private EditUserForm(final String id) {
			super(id);
		}

		@Override
		protected void onSubmit() {
			JaboxAuthenticatedWebSession session = getModelObject();
			User user = session.getUser();
			info("Saving User: " + user);
			_generalDao.persist(user);
		}
	}

	@SpringBean
	protected GeneralDao _generalDao;

	@SpringBean
	protected Manager<Connector> _manager;

	public EditUser() {
		Form<JaboxAuthenticatedWebSession> form = new EditUserForm("form");
		form.setModel(new CompoundPropertyModel<JaboxAuthenticatedWebSession>(
				getSession()));
		add(form);

		form.add(new RequiredTextField<User>("login", new PropertyModel<User>(
				this, "session.user.login")));
		form.add(new Label("firstName", new PropertyModel<User>(this,
				"session.user.firstName")));

		// Add a FeedbackPanel for displaying our messages
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		add(feedbackPanel);
	}
}
