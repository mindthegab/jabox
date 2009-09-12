package org.jabox.webapp.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.persistence.provider.GeneralDao;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.jabox.application.CreateProjectUtil;
import org.jabox.model.Project;
import org.jabox.webapp.borders.NavomaticBorder;

public class CreateProject extends WebPage {

	@SpringBean(name = "GeneralDao")
	protected GeneralDao generalDao;

	public CreateProject() {
		final Project _project = new Project();
		NavomaticBorder navomaticBorder = new NavomaticBorder("navomaticBorder");
		add(navomaticBorder);

		// Add a FeedbackPanel for displaying our messages
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		navomaticBorder.add(feedbackPanel);

		// Add a form with an onSumbit implementation that sets a message
		Form<Project> form = new Form<Project>("form") {
			private static final long serialVersionUID = -662744155604166387L;

			protected void onSubmit() {
				// We need to persist twice because the id is necessary for the
				// creation of the project.
				generalDao.persist(_project);
				new CreateProjectUtil().createProject(_project);
				info("input: " + _project);
				generalDao.persist(_project);
			}
		};
		form.setModel(new CompoundPropertyModel<Project>(_project));
		navomaticBorder.add(form);

		form.add(new RequiredTextField<Project>("name"));
		form.add(new RequiredTextField<Project>("description"));
	}
}