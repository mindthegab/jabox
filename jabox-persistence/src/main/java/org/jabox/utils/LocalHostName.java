package org.jabox.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LocalHostName {

	public static String getLocalHostname() {
		try {
			return InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return "localhost";
	}

}
