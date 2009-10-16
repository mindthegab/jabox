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
