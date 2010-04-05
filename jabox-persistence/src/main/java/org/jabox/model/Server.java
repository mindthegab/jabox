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

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.apache.wicket.persistence.domain.BaseEntity;

/**
 * 
 * @author dimitris
 */
@Entity
public class Server extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 4845825512187976712L;
	private String _name;
	private String _url;

	@OneToOne(mappedBy = "server", cascade = CascadeType.ALL)
	public DeployerConfig deployerConfig;

	public DeployerConfig getDeployerConfig() {
		return deployerConfig;
	}

	public void setDeployerConfig(final DeployerConfig newConfig) {
		deployerConfig = newConfig;
		newConfig.setServer(this);
	}

	public void setName(final String name) {
		_name = name;
	}

	public String getName() {
		return _name;
	}

	public void setUrl(final String url) {
		_url = url;
	}

	public String getUrl() {
		return _url;
	}
}
