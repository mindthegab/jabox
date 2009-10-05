package org.apache.wicket.persistence.provider;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.apache.wicket.persistence.domain.BaseEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.jabox.model.Configuration;
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
	public <T extends BaseEntity> T findEntity(Long id, Class<T> clazz) {
		Query query = entityManager.createQuery("SELECT e FROM "
				+ clazz.getName() + " e WHERE e.id=" + id);
		return (T) query.getSingleResult();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public <T extends BaseEntity> void persist(T object) {
		// if (object.getId() == null)
		entityManager.persist(object);
		// else {
		// entityManager.merge(object);
		// }

	}

	public void deleteEntity(BaseEntity entity) {
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

	@SuppressWarnings("unchecked")
	public <T extends BaseEntity> List<T> getEntities(Class<T> clazz) {
		Criteria criteria = getHibernateSession().createCriteria(clazz);
		List<T> entities = (List<T>) criteria.list();

		if (entities == null) {
			entities = new ArrayList<T>();
		}
		return entities;
	}
}
