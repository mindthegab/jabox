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
package org.jabox.scm.github;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * Acts as a wrapper model for the GitHub Account URL. On the webpage we ask for
 * the Account URL.
 */
public class GithubAccountURLModel implements IModel<String> {

	private final IModel<String> _serverContainingModel;

	private static final long serialVersionUID = -7026688595250460986L;

	private static final String HTTP = "http://";
	private static final String GIT = "git://";

	private static final String GITHUB_COM = "github.com/";

	/**
	 * Receives an addressModel that contains the server URL, and returns the
	 * Account URL.
	 * 
	 * @param serverURLModel
	 *            a Model that contains the server URL.
	 */
	public GithubAccountURLModel(final IModel<String> serverURLModel) {
		_serverContainingModel = serverURLModel;
	}

	/**
	 * Receives an address that contains the server URL, and returns the Account
	 * URL.
	 * 
	 * @param serverURL
	 *            the server URL.
	 */
	public GithubAccountURLModel(final String serverURL) {
		Model<String> model = new Model<String>(serverURL);
		_serverContainingModel = model;
	}

	/**
	 * Returns a String that represents the Account URL only without the PREFIX
	 * and SUFFIX.
	 */
	public String getObject() {
		String url = _serverContainingModel.getObject();
		if (url != null) {
			if (url.startsWith(HTTP + GITHUB_COM)) {
				url = url.substring(HTTP.length() + GITHUB_COM.length());
			}
		}
		return url;
	}

	public void setObject(final String object) {
		_serverContainingModel.setObject(HTTP + GITHUB_COM + object);
	}

	public void detach() {
		_serverContainingModel.detach();
	}

}
