package org.apache.wicket.persistence.provider;

import java.util.List;

import org.apache.wicket.persistence.domain.BaseEntity;
import org.jabox.model.Configuration;

public interface GeneralDao {

	<T extends BaseEntity> T findEntity(Long id, Class<T> clazz);

	<T extends BaseEntity> List<T> getEntities(Class<T> clazz);

	<T extends BaseEntity> void persist(T object);

	void deleteEntity(BaseEntity entity);

	Configuration getConfiguration();
}
