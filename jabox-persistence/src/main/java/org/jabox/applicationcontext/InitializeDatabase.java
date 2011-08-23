package org.jabox.applicationcontext;

import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.persistence.provider.ConfigXstreamDao;
import org.apache.wicket.persistence.provider.UserXstreamDao;
import org.jabox.model.User;

public class InitializeDatabase {

	public InitializeDatabase() {
		InjectorHolder.getInjector().inject(this);
	}

	/**
	 * check if database is already populated, if not, populate
	 */
	public void init() {
		if (ConfigXstreamDao.getConfig() == null) {
			User user = new User();
			user.setLogin("admin");
			user.setPassword("admin");
			UserXstreamDao.persist(user);
		}
	}
}
