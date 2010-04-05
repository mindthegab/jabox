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
package org.jabox.facades;

import java.util.List;

import org.apache.wicket.persistence.provider.GeneralDao;
import org.jabox.apis.IBaseEntity;
import org.jabox.cis.hudson.HudsonConnectorConfig;
import org.jabox.model.DefaultConfiguration;
import org.jabox.model.Server;

public class GeneralDaoMock implements GeneralDao {

	private final DefaultConfiguration _dc;

	public GeneralDaoMock() {
		_dc = new DefaultConfiguration();

		// Mock CIS
		HudsonConnectorConfig cis = new HudsonConnectorConfig();
		cis.setServer(new Server());
		cis.getServer().setName("HudsonMock");
		_dc.switchDefault(cis);
	}

	public void deleteEntity(final IBaseEntity entity) {

	}

	public <T extends IBaseEntity> T findEntity(final Long id,
			final Class<T> clazz) {
		return null;
	}

	public DefaultConfiguration getDefaultConfiguration() {
		return _dc;
	}

	public <T extends IBaseEntity> List<T> getEntities(final Class<T> clazz) {
		return null;
	}

	public <T extends IBaseEntity> void persist(final T object) {
	}
}
