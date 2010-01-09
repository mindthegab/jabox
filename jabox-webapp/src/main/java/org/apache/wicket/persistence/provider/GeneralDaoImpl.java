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
package org.apache.wicket.persistence.provider;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.jabox.apis.IBaseEntity;
import org.jabox.model.Configuration;
import org.jabox.model.DefaultConfiguration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class GeneralDaoImpl implements GeneralDao, InitializingBean {
	public void afterPropertiesSet() throws Exception {

	}

	private EntityManager entityManager;

	protected Session getHibernateSession() {
		return (Session) entityManager.getDelegate();
	}

	/**
	 * 
	 * @param entityManager
	 */
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	public void setEntityManager(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@SuppressWarnings("unchecked")
	public <T extends IBaseEntity> T findEntity(Long id, Class<T> clazz) {
		Query query = entityManager.createQuery("SELECT e FROM "
				+ clazz.getName() + " e WHERE e.id=" + id);
		return (T) query.getSingleResult();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public <T extends IBaseEntity> void persist(T object) {
		// if (object.getId() == null)
		entityManager.persist(object);
		// else {
		// entityManager.merge(object);
		// }

	}

	public void deleteEntity(IBaseEntity entity) {
		getHibernateSession().delete(entity);
	}

	public Configuration getConfiguration() {
		List<Configuration> configs = getEntities(Configuration.class);

		// If there is no configuration create one.
		Configuration config = null;
		if (configs == null || configs.size() < 1) {
			config = new Configuration();
			persist(config);
		} else {
			config = configs.get(0);
		}

		return config;
	}

	public DefaultConfiguration getDefaultConfiguration() {
		List<DefaultConfiguration> configs = getEntities(DefaultConfiguration.class);

		// If there is no configuration create one.
		DefaultConfiguration config = null;
		if (configs == null || configs.size() < 1) {
			config = new DefaultConfiguration();
			persist(config);
		} else {
			config = configs.get(0);
		}

		return config;
	}

	@SuppressWarnings("unchecked")
	public <T extends IBaseEntity> List<T> getEntities(Class<T> clazz) {
		Criteria criteria = getHibernateSession().createCriteria(clazz);
		List<T> entities = (List<T>) criteria.list();

		if (entities == null) {
			entities = new ArrayList<T>();
		}
		return entities;
	}
}
