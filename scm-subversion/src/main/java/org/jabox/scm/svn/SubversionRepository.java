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
package org.jabox.scm.svn;

import java.io.File;

import org.jabox.environment.Environment;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;

public class SubversionRepository {

	public static boolean isInitialized() {
		return getSubversionBaseDir().exists();
	}

	public static void initialize() {
		try {
			File tgtPath = getSubversionBaseDir();
			SVNRepositoryFactory.createLocalRepository(tgtPath, true, false);
		} catch (SVNException e) {
		}
	}

	public static File getSubversionBaseDir() {
		StringBuffer sb = new StringBuffer();
		sb.append(Environment.getDataDir());
		sb.append(File.separatorChar);
		sb.append("subversion");
		sb.append(File.separatorChar);
		return new File(sb.toString());
	}
}
