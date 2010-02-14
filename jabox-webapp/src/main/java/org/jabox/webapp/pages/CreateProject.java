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

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.feedback.ComponentFeedbackMessageFilter;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.persistence.provider.GeneralDao;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.jabox.application.CreateProjectUtil;
import org.jabox.model.MavenArchetype;
import org.jabox.model.Project;
import org.jabox.webapp.borders.MiddlePanel;

@AuthorizeInstantiation("ADMIN")
public class CreateProject extends MiddlePanel {

	@SpringBean(name = "GeneralDao")
	protected GeneralDao generalDao;

	public CreateProject() {
		final Project _project = new Project();
		MavenArchetype ma = new MavenArchetype("org.apache.wicket",
				"wicket-archetype-quickstart", "1.3.3");
		_project.setMavenArchetype(ma);

		// Add a form with an onSumbit implementation that sets a message
		Form<Project> form = new Form<Project>("form") {
			private static final long serialVersionUID = -662744155604166387L;

			@Override
			protected void onSubmit() {
				// We need to persist twice because the id is necessary for the
				// creation of the project.
				generalDao.persist(_project);
				new CreateProjectUtil().createProject(_project);
				generalDao.persist(_project);
				info("Project \"" + _project.getName() + "\" Created.");
			}
		};

		// Add a FeedbackPanel for displaying form messages
		add(new FeedbackPanel("feedback", new ComponentFeedbackMessageFilter(
				form)));

		form.setModel(new CompoundPropertyModel<Project>(_project));
		add(form);

		// Name
		FormComponent<Project> name = new RequiredTextField<Project>("name");
		form.add(new FeedbackPanel("nameFeedback",
				new ComponentFeedbackMessageFilter(name)));
		form.add(name);

		// Description
		RequiredTextField<Project> description = new RequiredTextField<Project>(
				"description");
		form.add(new FeedbackPanel("descriptionFeedback",
				new ComponentFeedbackMessageFilter(description)));
		form.add(description);

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
