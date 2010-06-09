package org.jabox.scm.svn;

import java.io.IOException;

import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.validator.UrlValidator;

public class SVNLoginValidator extends UrlValidator {
	private static final long serialVersionUID = 2635593287132542621L;
	private final TextField<String> _url;
	private final TextField<String> _username;
	private final PasswordTextField _password;

	public SVNLoginValidator(final TextField<String> url,
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

		try {
			boolean login = new SubversionFacade().validate(_url.getValue(),
					_username.getValue(), _password.getValue());

			if (!login) {
				error(_url.newValidatable());
			}
		} catch (IOException e) {
			error(_url.newValidatable());
		}
	}
}
