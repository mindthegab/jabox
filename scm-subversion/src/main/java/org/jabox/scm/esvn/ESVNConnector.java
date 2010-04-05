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
package org.jabox.scm.esvn;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.jabox.model.DeployerConfig;
import org.jabox.model.Server;
import org.jabox.scm.svn.SVNConnector;
import org.springframework.stereotype.Service;

@Service
/**
 * Embedded Subversion Connector.
 * <p>
 *
 * The "Service" annotation is injecting it to the list of the Plugins.
 */
public class ESVNConnector extends SVNConnector {
	private static final long serialVersionUID = -8772470110891207618L;

	/**
	 * Unique Plugin ID.
	 * <p/>
	 * TODO: This can be replaced by the unique package name.
	 */
	public static final String ID = "plugin.scm.esvn";

	/**
	 * The Displayable name of the Plugin.
	 * <p/>
	 * TODO: This can be replaced by i18n text in plugin.property.
	 */
	@Override
	public String getName() {
		return "Embedded Subversion";
	}

	/**
	 * Retrieves the Unique Plugin ID.
	 * <p/>
	 * TODO: This can be replaced by the unique package name.
	 */
	@Override
	public String getId() {
		return "plugin.scm.esvn";
	}

	/**
	 * Configuration Factory.
	 * <p/>
	 * TODO: To be decoupled.
	 */
	@Override
	public DeployerConfig newConfig() {
		return new ESVNConnectorConfig();
	}

	/**
	 * Editor Factory.
	 * <p/>
	 * TODO: To be decoupled.
	 */
	@Override
	public Component newEditor(final String id, final IModel<Server> model) {
		return new ESVNConnectorEditor(id, model);
	}
}
