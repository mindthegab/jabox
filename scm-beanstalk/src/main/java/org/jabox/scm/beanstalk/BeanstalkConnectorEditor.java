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
package org.jabox.scm.beanstalk;

import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.jabox.model.Server;

public class BeanstalkConnectorEditor extends Panel {
	private static final long serialVersionUID = -4137475647749541936L;

	public BeanstalkConnectorEditor(final String id, final IModel<Server> model) {
		super(id, new CompoundPropertyModel<String>(model));
		TextField<String> url = new TextField<String>("server.url",
				new BeanstalkAccountURLModel(new PropertyModel<String>(model,
						"server.url")));
		TextField<String> username = new TextField<String>("username");
		TextField<String> projectName = new TextField<String>("projectName");
		PasswordTextField password = new PasswordTextField("password");

		add(username.setRequired(true));
		add(projectName.setRequired(true));
		add(password.setRequired(true));
		add(url.add(
				new BeanstalkLoginValidator(url, username, projectName,
						password)).setRequired(true));
	}
}
