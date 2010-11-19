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

import java.util.List;

import org.jabox.apis.IBaseEntity;
import org.jabox.model.DefaultConfiguration;

public interface GeneralDao {

	<T extends IBaseEntity> T findEntity(Long id, Class<T> clazz);

	public <T extends IBaseEntity> T findEntityByQuery(final String fieldname,
			final String value, final Class<T> clazz);

	<T extends IBaseEntity> List<T> getEntities(Class<T> clazz);

	<T extends IBaseEntity> void persist(T object);

	void deleteEntity(IBaseEntity entity);

	DefaultConfiguration getDefaultConfiguration();

}
