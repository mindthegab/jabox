package org.jabox.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class DownloadHelper {
	/**
	 * 
	 * @param urlPath
	 * @return
	 */
	public static File downloadFile(String urlPath, File outputFile) {
		InputStream is = null;
		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;

		outputFile.getParentFile().mkdirs();

		try {
			URL url = new URL(urlPath);
			is = url.openStream();
			bin = new BufferedInputStream(is);
			bout = new BufferedOutputStream(new FileOutputStream(outputFile));
			byte[] buf = new byte[1024];
			while (bin.read(buf) > 0) {
				bout.write(buf);
			}
			bout.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				is.close();
				bin.close();
				bout.close();
			} catch (IOException ioe) {
				// nothing to see here
			}
		}
		return outputFile;
	}

}
