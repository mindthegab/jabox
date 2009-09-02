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
	public static File downloadFile(String urlPath, String prefix, String suffix) {
		InputStream is = null;
		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;
		try {
			URL url = new URL(urlPath);
			is = url.openStream();
			bin = new BufferedInputStream(is);
			File jtracZip = File.createTempFile(prefix, suffix);
			jtracZip.deleteOnExit();
			bout = new BufferedOutputStream(new FileOutputStream(jtracZip));
			while (true) {
				int datum = bin.read();
				if (datum == -1)
					break;
				bout.write(datum);
			}
			bout.flush();
			return jtracZip;
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
		return null;
	}

}
