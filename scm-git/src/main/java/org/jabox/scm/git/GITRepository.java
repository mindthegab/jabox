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
package org.jabox.scm.git;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.jabox.environment.Environment;

public class GITRepository {

	public static boolean isInitialized() {
		return getGitBaseDir().exists();
	}

	/**
	 * It will initialize the local GIT repo. In case it already exists, it will
	 * leave it as is.
	 */
	public static void initialize() {
		File tgtPath = getGitBaseDir();
		tgtPath.mkdirs();
		try {
			Process p = Runtime.getRuntime().exec("git init", null, tgtPath);
			InputStream istrm = p.getInputStream();
			InputStreamReader istrmrdr = new InputStreamReader(istrm);
			BufferedReader buffrdr = new BufferedReader(istrmrdr);

			String data;
			while ((data = buffrdr.readLine()) != null) {
				System.out.println(data);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static File getGitBaseDir() {
		StringBuffer sb = new StringBuffer();
		sb.append(Environment.getBaseDir());
		sb.append("gitRepo");
		sb.append(File.separatorChar);
		return new File(sb.toString());
	}
}
