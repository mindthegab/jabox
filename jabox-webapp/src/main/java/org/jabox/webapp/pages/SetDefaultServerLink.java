package org.jabox.webapp.pages;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.persistence.provider.GeneralDao;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.jabox.apis.ConnectorConfig;
import org.jabox.model.DefaultConfiguration;

public class SetDefaultServerLink extends Link<ConnectorConfig> {
	private static final long serialVersionUID = -2146843131170907255L;
	@SpringBean
	protected GeneralDao _generalDao;

	public SetDefaultServerLink(String id, IModel<ConnectorConfig> model) {
		super(id, model);
	}

	@Override
	public void onClick() {
		DefaultConfiguration dc = _generalDao.getDefaultConfiguration();
		dc.setDefault(getModelObject());
		_generalDao.persist(dc);
	}

}
