package org.jabox.webapp.pages;

import java.util.ArrayList;
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
import org.jabox.application.CreateProjectUtil;
import org.jabox.model.MavenArchetype;
import org.jabox.model.Project;
import org.jabox.webapp.borders.NavomaticBorder;

@AuthorizeInstantiation("ADMIN")
public class CreateProject extends BasePage {

	@SpringBean(name = "GeneralDao")
	protected GeneralDao generalDao;

	public CreateProject() {
		final Project _project = new Project();
		MavenArchetype ma = new MavenArchetype("org.apache.wicket",
				"wicket-archetype-quickstart", "1.3.3");
		_project.setMavenArchetype(ma);
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

		List<MavenArchetype> connectors = new ArrayList<MavenArchetype>();
		connectors.add(ma);
		fillArchetypes(connectors);
		System.out.println("connectors: " + ":" + connectors);

		DropDownChoice<MavenArchetype> ddc = new DropDownChoice<MavenArchetype>(
				"archetype", new PropertyModel<MavenArchetype>(_project,
						"mavenArchetype"), connectors,
				new ChoiceRenderer<MavenArchetype>("toString", "toString"));
		form.add(ddc);

	}

	private void fillArchetypes(List<MavenArchetype> connectors) {
		connectors.add(new MavenArchetype("org.apache.wicket",
				"wicket-archetype-quickstart", "1.4.1"));
		connectors.add(new MavenArchetype("org.apache.maven.archetypes",
				"maven-archetype-quickstart", "1.0"));
		connectors.add(new MavenArchetype("org.apache.maven.archetypes",
				"maven-archetype-site", "1.0"));
		connectors.add(new MavenArchetype("org.apache.maven.archetypes",
				"maven-archetype-site-simple", "1.0"));
		connectors.add(new MavenArchetype("org.apache.maven.archetypes",
				"maven-archetype-webapp", "1.0"));
	}
}