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
package org.jabox.embedded.h2;

import java.sql.SQLException;

import org.h2.tools.Server;

public class H2Server {
	public static void main(final String[] args) {
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
