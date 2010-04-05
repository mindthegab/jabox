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
package org.jabox.eclipse_startup;

import java.io.File;

public class EclipseRunnerWindows extends EclipseRunner {

	@Override
	public String getDownloadURL() {
		return "http://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/galileo/R/eclipse-jee-galileo-win32.zip&url=http://ftp.wh2.tu-dresden.de/pub/mirrors/eclipse/technology/epp/downloads/release/galileo/R/eclipse-jee-galileo-win32.zip&mirror_id=324";
	}

	@Override
	public String getFileName() {
		return "eclipse-jee-galileo-win32.zip";
	}

	@Override
	public File getEclipseExecutable(final File eclipseHome) {
		String absolutePath = eclipseHome.getAbsolutePath();
		File eclipseDir = new File(absolutePath, "eclipse");
		return new File(eclipseDir, "eclipse.exe");
	}
}
