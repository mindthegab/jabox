package org.jabox.webapp.panels;

import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.persistence.provider.GeneralDao;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.jabox.webapp.modifiers.TooltipModifier;

public class TabsList extends PropertyListView<Tab> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2877438240039632971L;

	public TabsList(String id, List<Tab> projects) {
		super(id, projects);
	}

	@SpringBean(name = "GeneralDao")
	protected GeneralDao generalDao;

	@Override
	public void populateItem(final ListItem<Tab> listItem) {
		final Tab tab = listItem.getModelObject();

		if (tab.isSelected()) {
			listItem.add(new AttributeModifier("class", new Model<String>(
					"selected")));
		}
		listItem.add(new TooltipModifier(tab.getTooltip()));

		ExternalLink externalLink = new ExternalLink("url", tab.getUrl());
		externalLink.add(new Label("title", tab.getTitle()));

		listItem.add(externalLink);
	}
}
