package org.jabox.webapp.pages;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.persistence.domain.BaseEntityDetachableModel;
import org.apache.wicket.persistence.provider.GeneralDao;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.jabox.model.Server;

public class EditServerLink extends Link<Server> {
	private static final long serialVersionUID = 5875706844558592404L;

	@SpringBean
	protected GeneralDao _generalDao;

	public EditServerLink(String id, IModel<Server> model) {
		super(id, model);
	}

	@Override
	public void onClick() {
		IModel<Server> model = new BaseEntityDetachableModel<Server>(
				getModelObject());
		setResponsePage(new EditServerPage(new CompoundPropertyModel<Server>(
				model)) {

			@Override
			protected void onCancel() {
				setResponsePage(ManageServers.class);
			}

			@Override
			protected void onSave(Server server) {
				_generalDao.persist(server);
				setResponsePage(ManageServers.class);
			}
		});
	}
}
