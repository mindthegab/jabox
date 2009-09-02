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

@Service
public class EmbededSVNConnector implements SCMConnector, Serializable {

	private static final long serialVersionUID = -3875844507330633672L;

	private File _tmpDir;

	@Override
	public String toString() {
		return "Embeded Subversion Plugin";
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
