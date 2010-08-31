package org.jabox.cis.hudson;

import java.io.IOException;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.validator.UrlValidator;

public class HudsonLoginValidator extends UrlValidator {
	private static final long serialVersionUID = 2635593287132542621L;
	private final TextField<String> _url;
	private final TextField<String> _username;
	private final PasswordTextField _password;

	public HudsonLoginValidator(final TextField<String> url,
			final TextField<String> username, final PasswordTextField password) {
		_url = url;
		_username = username;
		_password = password;
	}

	@Override
	protected void onValidate(final IValidatable<String> validatable) {
		if (!_url.isValid() || !_username.isValid() || !_password.isValid()) {
			return;
		}

		HttpClient client = new HttpClient();
		Credentials creds = new UsernamePasswordCredentials(_username
				.getValue(), _password.getValue());
		client.getState().setCredentials(null, null, creds);

		client.getState().setAuthenticationPreemptive(true);

		GetMethod get = new GetMethod(_url.getValue());

		try {
			int result = client.executeMethod(get);
			if (result == 401) {
				error(validatable);
			}
		} catch (HttpException e) {
			error(validatable);
			e.printStackTrace();
		} catch (IOException e) {
			error(validatable);
			e.printStackTrace();
		} finally {
			get.releaseConnection();
		}
	}
}
