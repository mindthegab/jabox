package org.jabox.eclipse_startup;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.ice.tar.TarEntry;
import com.ice.tar.TarInputStream;

public class UntarEclipse {
	public static void untar(final InputStream in, final String untarDir)
			throws IOException {

		System.out.println("Reading TarInputStream... ");
		TarInputStream tin = new TarInputStream(in);
		TarEntry tarEntry = tin.getNextEntry();
		if (new File(untarDir).exists()) {
			while (tarEntry != null) {
				File destPath = new File(untarDir + File.separatorChar
						+ tarEntry.getName());
				System.out.println("Processing " + destPath.getAbsoluteFile());
				if (!tarEntry.isDirectory()) {
					FileOutputStream fout = new FileOutputStream(destPath);
					tin.copyEntryContents(fout);
					fout.close();
				} else {
					destPath.mkdir();
				}
				tarEntry = tin.getNextEntry();
			}
			tin.close();
		} else {
			System.out.println("That destination directory doesn't exist! "
					+ untarDir);
		}

	}

}
