package org.jabox.svn;

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
		sb.append(Environment.getBaseDir());
		sb.append("svnRepo");
		sb.append(File.separatorChar);
		return new File(sb.toString());
	}
}
