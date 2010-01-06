package org.jabox.webapp.pages;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.persistence.provider.GeneralDao;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.jabox.model.DeployerConfig;
import org.jabox.model.Server;

public class CreateServerLink extends Link<Void> {
	private static final long serialVersionUID = -6076134805074401259L;

	@SpringBean
	protected GeneralDao _generalDao;

	public CreateServerLink(String id) {
		super(id);
	}

	@Override
	public void onClick() {
		IModel<Server> model = new Model<Server>(new Server());
		setResponsePage(new EditServerPage(model) {

			@Override
			protected void onCancel() {
				setResponsePage(ManageServers.class);
			}

			@Override
			protected void onSave(Server article) {
				_generalDao.persist(article);
				setResponsePage(ManageServers.class);
			}
		});
	}
}
