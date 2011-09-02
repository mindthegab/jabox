package org.apache.wicket.persistence.provider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jabox.environment.Environment;
import org.jabox.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;

public class UserXstreamDao {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserXstreamDao.class);

	private static XStream getXStream() {
		XStream xstream = new XStream();
		xstream.alias("user", User.class);
		return xstream;
	}

	public static void persist(User user) {
		XStream xstream = getXStream();
		String xml = xstream.toXML(user);
		try {
			File usersDir = Environment.getUsersDir();
			File file = new File(usersDir, user.getLogin() + ".xml");
			FileWriter writer = new FileWriter(file);
			writer.write(xml);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<User> getUsers() {
		ArrayList<User> users = new ArrayList<User>();
		File usersDir = Environment.getUsersDir();

		String[] children = usersDir.list();
		if (children == null) {
			// Either dir does not exist or is not a directory
		} else {
			for (int i = 0; i < children.length; i++) {
				// Get filename of file or directory
				String filename = children[i];
				String login = filename.replaceAll(".xml$", "");
				users.add(getUser(login));
			}
		}

		return users;
	}

	public static User getUser(String login) {
		XStream xstream = getXStream();

		File usersDir = Environment.getUsersDir();
		File file = new File(usersDir, login + ".xml");

		try {
			FileInputStream is = new FileInputStream(file);
			User user = (User) xstream.fromXML(is);
			return user;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void deleteUser(User user) {
		File file = new File(Environment.getUsersDir(), user.getLogin()
				+ ".xml");
		file.delete();
		LOGGER.info("User \"" + user.getLogin() + "\" deleted");
	}
}
