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

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.jabox.apis.IBaseEntity;

@MappedSuperclass
public abstract class BaseEntity implements IBaseEntity {
	private static final long serialVersionUID = -102086082614997832L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/**
	 * The version number for optimistic locking.
	 */
	@Version
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer version;

	public Long getId() {
		return id;
	}

	/**
	 * This method has private accessibility because Hibernate mandates that it
	 * exists but it really should not. This is best effort Public because
	 * interface needs it
	 */
	public final void setId(final Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(final Integer version) {
		this.version = version;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		if (id == null) {
			return false;
		}

		final BaseEntity other = (BaseEntity) o;
		if (!id.equals(other.id)) {
			return false;
		}

		return true;
	}

}
