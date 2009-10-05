package org.jabox.webapp.utils;

import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.persistence.provider.GeneralDao;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.jabox.model.Project;
import org.jabox.webapp.pages.DeleteEntityButton;

public class ProjectList extends PropertyListView<Project> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2877438240039632971L;

	public ProjectList(String id, List<Project> projects) {
		super(id, projects);
	}

	@SpringBean(name = "GeneralDao")
	protected GeneralDao generalDao;

	public void populateItem(final ListItem<Project> listItem) {
		final Project project = (Project) listItem.getModelObject();
		listItem.add(new Label("name", project.getName()));
		listItem
				.add(new MultiLineLabel("description", project.getDescription()));
		final AttributeModifier attributeModifier = new AttributeModifier(
				"class", true, new EvenOddRow<Project>(listItem));
		listItem.add(attributeModifier);
		listItem.add(new DeleteEntityButton<Project>("delete", listItem));
	}
}
