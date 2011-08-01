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

import com.thoughtworks.xstream.XStream;

public class UserXstreamDao {

	public static void persist(User user) {
		XStream xstream = new XStream();
		xstream.alias("user", User.class);
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
		XStream xstream = new XStream();
		xstream.alias("user", User.class);

		users.add(getUser("admin"));
		return users;
	}

	public static User getUser(String login) {
		XStream xstream = new XStream();
		xstream.alias("user", User.class);

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

}
