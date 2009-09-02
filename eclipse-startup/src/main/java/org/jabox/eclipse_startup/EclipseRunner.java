package org.jabox.eclipse_startup;

import java.io.File;
import java.io.IOException;

public abstract class EclipseRunner {
	abstract String getFileName();

	public static EclipseRunner getInstance() {
		if (OSHelper.isWindowsPlatform()) {
			return new EclipseRunnerWindows();
		} else {
			return new EclipseRunnerLinux();
		}
	}

	abstract String getDownloadURL();

	abstract String getEclipseExecutable(final File eclipseHome);

	public void executeEclipse(File eclipseHome) throws IOException {
		Runtime.getRuntime().exec(
				new String[] { getEclipseExecutable(eclipseHome) });
	}
}
