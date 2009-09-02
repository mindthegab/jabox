package org.jabox.embedded.h2;

import java.sql.SQLException;

import org.h2.tools.Server;

public class H2Server {
	public static void main(String[] args) {
		new H2Server();
	}
	public H2Server() {
		System.out.println("Starting H2");

		String[] args = new String[0];
		// start the TCP Server
		Server server;
		try {
			server = Server.createTcpServer(args).start();
			Server.createWebServer(args).start();
			// stop the TCP Server
			server.stop();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
