package org.jabox.scm.github;

import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.validator.UrlValidator;
import org.jabox.model.Server;

public class GithubLoginValidator extends UrlValidator {
	private static final long serialVersionUID = 2635593287132542621L;
	private final TextField<String> _url;
	private final TextField<String> _username;
	private final TextField<String> _projectName;
	private final PasswordTextField _password;

	public GithubLoginValidator(final TextField<String> url,
			final TextField<String> username, TextField<String> projectName,
			final PasswordTextField password) {
		_url = url;
		_username = username;
		_projectName = projectName;
		_password = password;
	}

	@Override
	protected void onValidate(final IValidatable<String> validatable) {
		if (!_url.isValid() || !_username.isValid() || !_projectName.isValid()
				|| !_password.isValid()) {
			return;
		}

		GithubConnectorConfig beanstalkcc = new GithubConnectorConfig();
		beanstalkcc.server = new Server();
		beanstalkcc.server.setUrl(_url.getValue());
		beanstalkcc.username = _username.getValue();
		beanstalkcc.projectName = _projectName.getValue();
		beanstalkcc.password = _password.getValue();

		// try {
		// boolean login = new SubversionFacade().validate(beanstalkcc
		// .getScmUrl(), _username.getValue(), _password.getValue());
		//
		// if (!login) {
		// error(_url.newValidatable());
		// }
		// } catch (IOException e) {
		// error(_url.newValidatable());
		// }
	}
}
