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

		private EditUserForm(String id) {
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
