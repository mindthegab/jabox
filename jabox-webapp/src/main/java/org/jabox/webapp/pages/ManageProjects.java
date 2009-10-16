package org.jabox.webapp.pages;

import java.util.List;

import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.persistence.domain.BaseEntity;
import org.apache.wicket.persistence.provider.GeneralDao;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.jabox.model.Project;
import org.jabox.webapp.borders.MiddlePanel;
import org.jabox.webapp.utils.ProjectList;

/**
 * Homepage
 */
@AuthorizeInstantiation("ADMIN")
public class ManageProjects extends MiddlePanel {

	private static final long serialVersionUID = 1L;

	@SpringBean(name = "GeneralDao")
	protected GeneralDao generalDao;

	public ManageProjects() {
		final List<Project> projects = generalDao.getEntities(Project.class);
		Form<BaseEntity> form = new Form<BaseEntity>("deleteForm");
		form.add(new ProjectList("projects", projects));
		add(form);
	}
}
