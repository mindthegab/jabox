package org.jabox.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.apache.wicket.persistence.domain.BaseEntity;

@Entity
public class User extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 840333278259987092L;
	@Column(nullable = false)
	private String _login;
	private String _firstName;
	private String _lastName;
	private char[] _password;
	private String _email;

	public String getLogin() {
		return _login;
	}

	public void setLogin(String login) {
		_login = login;
	}

	public String getFirstName() {
		return _firstName;
	}

	public void setFirstName(String firstName) {
		_firstName = firstName;
	}

	public String getLastName() {
		return _lastName;
	}

	public void setLastName(String lastName) {
		_lastName = lastName;
	}

	public char[] getPassword() {
		return _password;
	}

	public void setPassword(char[] password) {
		_password = password;
	}

	public String getEmail() {
		return _email;
	}

	public void setEmail(String email) {
		_email = email;
	}

	@Override
	public String toString() {
		return _login;
	}
}
