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
package org.jabox.standalone;

import java.io.IOException;
import java.net.URI;

/**
 * Starts the Browser.
 * 
 */
public class BrowserStarter {

	private static final String BROWSE = "browse";
	private static final String GET_DESKTOP = "getDesktop";
	private static final String JAVA_AWT_DESKTOP = "java.awt.Desktop";
	private static final String IS_DESKTOP_SUPPORTED = "isDesktopSupported";
	private static final String WINDOWS = "windows";

	/**
	 * Open a new browser tab or window with the given URL.
	 * 
	 * @param url
	 *            the URL to open
	 */
	public static void openBrowser(final String url) {
		// XXX make osName dynamic
		String osName = "MS windows";
		Runtime rt = Runtime.getRuntime();
		try {
			try {
				Class<?> desktopClass = Class.forName(JAVA_AWT_DESKTOP);
				Boolean supported = (Boolean) desktopClass.getMethod(
						IS_DESKTOP_SUPPORTED).invoke(null, new Object[0]);
				URI uri = new URI(url);
				if (supported) {
					Object desktop = desktopClass.getMethod(GET_DESKTOP)
							.invoke(null, new Object[0]);
					desktopClass.getMethod(BROWSE, URI.class).invoke(desktop,
							uri);
					return;
				}
			} catch (Exception e) {
			}
			if (osName.indexOf(WINDOWS) >= 0) {
				rt.exec(new String[] { "rundll32",
						"url.dll,FileProtocolHandler", url });
			} else if (osName.indexOf("mac") >= 0) {
				// Mac OS: to open a page with Safari, use "open -a Safari"
				Runtime.getRuntime().exec(new String[] { "open", url });
			} else {
				String[] browsers = { "firefox", "mozilla-firefox", "mozilla",
						"konqueror", "netscape", "opera" };
				boolean ok = false;
				for (String b : browsers) {
					try {
						rt.exec(new String[] { b, url });
						ok = true;
						break;
					} catch (Exception e) {
						// ignore and try the next
					}
				}
				if (!ok) {
					// No success in detection.
					System.out
							.println("Please open a browser and go to " + url);
				}
			}
		} catch (IOException e) {
			System.out.println("Failed to start a browser to open the URL "
					+ url);
			e.printStackTrace();
		}
	}

}