/*
 * Jabox Open Source Version
 * Copyright (C) 2009-2010 Dimitris Kapanidis                                                                                                                          
 * 
 * This file is part of Jabox
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
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
	public <T extends IBaseEntity> T findEntity(final Long id,
			final Class<T> clazz) {
		Query query = entityManager.createQuery("SELECT e FROM "
				+ clazz.getName() + " e WHERE e.id=" + id);
		return (T) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public <T extends IBaseEntity> T findEntityByQuery(final String fieldname,
			final String value, final Class<T> clazz) {
		Query query = entityManager.createQuery("SELECT e FROM "
				+ clazz.getName() + " e WHERE e." + fieldname + "='" + value
				+ "'");
		return (T) query.getSingleResult();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public <T extends IBaseEntity> void persist(final T object) {
		// if (object.getId() == null)
		entityManager.persist(object);
		// else {
		// entityManager.merge(object);
		// }

	}

	public void deleteEntity(final IBaseEntity entity) {
		getHibernateSession().delete(entity);
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
	public <T extends IBaseEntity> List<T> getEntities(final Class<T> clazz) {
		Criteria criteria = getHibernateSession().createCriteria(clazz);
		List<T> entities = criteria.list();

		if (entities == null) {
			entities = new ArrayList<T>();
		}
		return entities;
	}
}
