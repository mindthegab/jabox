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
package org.jabox.apis.cis;

import org.jabox.apis.ConnectorConfig;
import org.jabox.model.Project;

public interface CISConnectorConfig extends ConnectorConfig {

	/**
	 * @return The name of the continuous integration system, e.g.
	 *         <code>continuum</code>.
	 */
	public String getSystem();

	/**
	 * @param project
	 *            The project
	 * @return The URL of the Job that builds the project inside the continuous
	 *         integration system.
	 */
	public String getJobUrl(Project project);
}
