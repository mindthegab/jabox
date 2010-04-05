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
package org.jabox.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jabox.apis.Connector;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class IdentifiableBeanRegistry<T extends Identifiable<ID>, ID extends Serializable>
		implements ApplicationContextAware, InitializingBean {
	private final Class<T> beanType;

	private ApplicationContext context;
	private Map<ID, T> entries;
	private List<T> beans;
	private List<ID> uuids;

	public IdentifiableBeanRegistry(final Class<T> beanType) {
		this.beanType = beanType;
	}

	public void setApplicationContext(final ApplicationContext context)
			throws BeansException {
		this.context = context;
	}

	public T getEntry(final ID id) {
		T entry = entries.get(id);
		if (entry == null) {
			throw new IllegalStateException();
		}
		return entry;
	}

	public List<T> getEntries() {
		return beans;
	}

	public List<ID> getIds() {
		return uuids;
	}

	/**
	 * Return the IDs that implement the specific interface.
	 * 
	 * @param interfaceClass
	 * @return a list of IDs of the implementations of this interface.
	 */
	public List<ID> getIds(final Class<? extends Connector> interfaceClass) {
		List<ID> list = new ArrayList<ID>();
		for (Entry<ID, T> entry : entries.entrySet()) {
			if (interfaceClass.isAssignableFrom(entry.getValue().getClass())) {
				list.add(entry.getKey());
			}
		}
		return Collections.unmodifiableList(list);
	}

	@SuppressWarnings("unchecked")
	public void afterPropertiesSet() throws Exception {
		// initialize the registry
		entries = new HashMap<ID, T>();

		final Map<String, ? extends T> matches = BeanFactoryUtils
				.beansOfTypeIncludingAncestors(context, beanType);
		for (T entry : matches.values()) {
			entries.put(entry.getId(), entry);
		}

		entries = Collections.unmodifiableMap(entries);

		// intialize indexes
		beans = new ArrayList<T>(entries.values());
		beans = Collections.unmodifiableList(beans);
		uuids = new ArrayList<ID>(entries.keySet());
		uuids = Collections.unmodifiableList(uuids);
	}
}
