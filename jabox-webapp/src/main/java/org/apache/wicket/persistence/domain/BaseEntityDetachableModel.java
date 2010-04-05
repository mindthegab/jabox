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
package org.apache.wicket.persistence.domain;

import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.persistence.provider.GeneralDao;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class BaseEntityDetachableModel<E extends BaseEntity> extends
		LoadableDetachableModel<E> {
	private static final long serialVersionUID = 501303796886245828L;

	@SpringBean(name = "GeneralDao")
	protected GeneralDao generalDao;

	private Long _id;
	private Class<E> _clazz;

	public BaseEntityDetachableModel() {
		InjectorHolder.getInjector().inject(this);
	}

	@SuppressWarnings("unchecked")
	public BaseEntityDetachableModel(final E baseEntity) {
		this();
		_id = baseEntity.getId();
		_clazz = (Class<E>) baseEntity.getClass();

	}

	@SuppressWarnings("unchecked")
	public void setBaseEntityDetachableModel(final BaseEntity baseEntity) {
		_id = baseEntity.getId();
		_clazz = (Class<E>) baseEntity.getClass();
	}

	@Override
	protected E load() {
		if (_clazz != null) {

			return generalDao.findEntity(_id, _clazz);
		} else {
			return null;
		}
	}

}
