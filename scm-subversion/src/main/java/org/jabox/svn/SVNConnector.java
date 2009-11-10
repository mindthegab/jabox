/*
 * The MIT License
 *
 * Copyright (c) 2009 Dimitris Kapanidis
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.jabox.svn;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import org.jabox.apis.scm.SCMConnector;
import org.jabox.apis.scm.SCMException;
import org.jabox.model.Project;
import org.jabox.utils.TemporalDirectory;
import org.springframework.stereotype.Service;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;

@Service
public class SVNConnector implements SCMConnector, Serializable {

	private static final long serialVersionUID = -3875844507330633672L;

	private File _tmpDir;

	@Override
	public String toString() {
		return "Subversion Plugin";
	}

	public String getScmUrl()  {
		SVNURL svnDir;
		try {
			svnDir = SVNURL.fromFile(SubversionRepository
					.getSubversionBaseDir());
			return svnDir.getPath();
		} catch (SVNException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public File createProjectDirectories(Project project) throws SCMException {
		try {
			SubversionFacade svn = new SubversionFacade();
			_tmpDir = TemporalDirectory.createTempDir();

			// Checkout basedir from subversion.
			svn.checkoutBaseDir(_tmpDir);
			// Create Project directory and its trunk/branches/tags
			// subdirectories
			File trunkDir = createProjectDirectories(project, _tmpDir);
			return trunkDir;

		} catch (IOException e) {
			throw new SCMException("Problem creating temporal directory.", e);
		} catch (SVNException e) {
			throw new SCMException("Problem during the Subversion checkout.", e);
		}
	}

	/**
	 * 
	 * @param project
	 * @param tmpDir
	 * @return the trunk directory.
	 */
	private static File createProjectDirectories(Project project, File tmpDir) {
		assert (tmpDir.exists());

		File projectDir = new File(tmpDir, project.getName());
		projectDir.mkdir();
		File trunkDir = new File(projectDir, "trunk");
		trunkDir.mkdir();
		new File(projectDir, "branches").mkdir();
		new File(projectDir, "tags").mkdir();
		return trunkDir;
	}

	public void commitProject(Project project) throws SCMException {
		assert (_tmpDir != null && _tmpDir.exists());

		try {
			SubversionFacade svn = new SubversionFacade();
			svn.commitProject(project, _tmpDir);
		} catch (SVNException e) {
			throw new SCMException("Problem during the Subversion commit.", e);
		}
	}
}
