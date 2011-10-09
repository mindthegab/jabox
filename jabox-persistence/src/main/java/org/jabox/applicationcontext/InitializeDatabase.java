package org.jabox.applicationcontext;

import org.apache.wicket.persistence.provider.UserXstreamDao;
import org.jabox.model.User;

public class InitializeDatabase {

	/**
	 * check if database is already populated, if not, populate
	 */
	public void init() {
		if (UserXstreamDao.getUsers().size() == 0) {
			User user = new User();
			user.setLogin("admin");
			user.setPassword("admin");
			UserXstreamDao.persist(user);
		}
	}
}
