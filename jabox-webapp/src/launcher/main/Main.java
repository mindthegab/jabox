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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.JarURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Iterator;

/**
 * Launcher class for stand-alone execution of Jabox as
 * <tt>java -jar jabox.jar</tt>.
 * 
 * Credit goes to Kohsuke Kawaguchi for his implementation in hudson.
 * 
 * @author Kohsuke Kawaguchi
 */
public class Main {
	public static void main(String[] args) throws Exception {
		// this is so that JFreeChart can work nicely even if we are launched as
		// a daemon
		System.setProperty("java.awt.headless", "true");

		File me = whoAmI();

		// locate Winstone jar
		URL jar = Main.class.getResource("winstone.jar");

		// put this jar in a file system so that we can load jars from there
		File tmpJar;
		try {
			tmpJar = File.createTempFile("winstone", "jar");
		} catch (IOException e) {
			String tmpdir = System.getProperty("java.io.tmpdir");
			IOException x = new IOException(
					"Hudson has failed to create a temporary file in " + tmpdir);
			x.initCause(e);
			throw x;
		}
		copyStream(jar.openStream(), new FileOutputStream(tmpJar));
		tmpJar.deleteOnExit();

		// clean up any previously extracted copy, since
		// winstone doesn't do so and that causes problems when newer version of
		// Jabox is deployed.
		File tempFile = File.createTempFile("dummy", "dummy");
		deleteContents(new File(tempFile.getParent(), "winstone/"
				+ me.getName()));
		tempFile.delete();

		// locate the Winstone launcher
		ClassLoader cl = new URLClassLoader(new URL[] { tmpJar.toURL() });
		Class launcher = cl.loadClass("winstone.Launcher");
		Method mainMethod = launcher.getMethod("main",
				new Class[] { String[].class });

		// figure out the arguments
		List arguments = new ArrayList(Arrays.asList(args));
		arguments.add(0, "--warfile=" + me.getAbsolutePath());
		if (!hasWebRoot(arguments))
			// defaults to ~/.hudson/war since many users reported that cron job
			// attempts to clean up
			// the contents in the temporary directory.
			arguments.add("--webroot="
					+ new File(System.getProperty("user.home"), ".jabox/war"));

		// run
		mainMethod.invoke(null,
				new Object[] { arguments.toArray(new String[0]) });
	}

	private static boolean hasWebRoot(List arguments) {
		for (Iterator itr = arguments.iterator(); itr.hasNext();) {
			String s = (String) itr.next();
			if (s.startsWith("--webroot="))
				return true;
		}
		return false;
	}

	/**
	 * Figures out the URL of <tt>jabox.war</tt>.
	 */
	public static File whoAmI() throws IOException, URISyntaxException {
		URL classFile = Main.class.getClassLoader().getResource("Main.class");

		// JNLP returns the URL where the jar was originally placed
		// not the local cached file. So we need a rather round about approach
		// to get to
		// the local file name.
		return new File(((JarURLConnection) classFile.openConnection())
				.getJarFile().getName());
	}

	private static void copyStream(InputStream in, OutputStream out)
			throws IOException {
		byte[] buf = new byte[8192];
		int len;
		while ((len = in.read(buf)) > 0)
			out.write(buf, 0, len);
		in.close();
		out.close();
	}

	private static void deleteContents(File file) throws IOException {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			if (files != null) {// be defensive
				for (int i = 0; i < files.length; i++)
					deleteContents(files[i]);
			}
		}
		file.delete();
	}
}
