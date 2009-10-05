package org.jabox.webapp.pages;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.form.ImageButton;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.persistence.domain.BaseEntity;
import org.apache.wicket.persistence.provider.GeneralDao;
import org.apache.wicket.spring.injection.annot.SpringBean;

public final class DeleteEntityButton extends ImageButton {
	private static final ResourceReference DELETE_IMG = new ResourceReference(
			DeleteEntityButton.class, "delete.jpg");
	private final ListItem<BaseEntity> _listItem;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DeleteEntityButton(final String id,
			final ListItem<BaseEntity> listItem) {
		super(id, DELETE_IMG);
		_listItem = listItem;
	}

	@SpringBean(name = "GeneralDao")
	protected GeneralDao generalDao;

	/**
	 * Delete from persistent storage, commit transaction.
	 */
	public void onSubmit() {
		generalDao.deleteEntity(_listItem.getModelObject());
		setResponsePage(ManageProjects.class);
	}
}