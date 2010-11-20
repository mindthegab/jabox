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

import javax.persistence.Column;
import javax.persistence.Entity;

import org.apache.wicket.persistence.domain.BaseEntity;

@Entity
public class User extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 840333278259987092L;
	@Column(nullable = false)
	private String _login;
	private String _firstName;
	private String _lastName;
	private String _password;
	private String _email;

	public String getLogin() {
		return _login;
	}

	public void setLogin(final String login) {
		_login = login;
	}

	public String getFirstName() {
		return _firstName;
	}

	public void setFirstName(final String firstName) {
		_firstName = firstName;
	}

	public String getLastName() {
		return _lastName;
	}

	public void setLastName(final String lastName) {
		_lastName = lastName;
	}

	public String getPassword() {
		return _password;
	}

	public void setPassword(final String password) {
		_password = password;
	}

	public String getEmail() {
		return _email;
	}

	public void setEmail(final String email) {
		_email = email;
	}

	@Override
	public String toString() {
		return _login;
	}
}
