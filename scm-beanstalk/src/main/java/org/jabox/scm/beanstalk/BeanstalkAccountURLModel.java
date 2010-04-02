package org.jabox.scm.beanstalk;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * Acts as a wrapper model for the Beanstalk Account URL. On the webpage we ask
 * for the Account URL.
 */
public class BeanstalkAccountURLModel implements IModel<String> {

	private static final String BEANSTALKAPP_COM = ".beanstalkapp.com";

	private final IModel<String> _serverContainingModel;

	private static final long serialVersionUID = -7026688595250460986L;

	private static final String HTTPS = "https://";

	/**
	 * Receives an addressModel that contains the server URL, and returns the
	 * Account URL.
	 * 
	 * @param serverURLModel
	 *            a Model that contains the server URL.
	 */
	public BeanstalkAccountURLModel(final IModel<String> serverURLModel) {
		_serverContainingModel = serverURLModel;
	}

	/**
	 * Receives an address that contains the server URL, and returns the Account
	 * URL.
	 * 
	 * @param serverURL
	 *            the server URL.
	 */
	public BeanstalkAccountURLModel(final String serverURL) {
		Model<String> model = new Model<String>(serverURL);
		_serverContainingModel = model;
	}

	/**
	 * Returns a String that represents the Account URL only without the PREFIX
	 * and SUFFIX.
	 */
	public String getObject() {
		String url = _serverContainingModel.getObject();
		if (url != null) {
			if (url.startsWith(HTTPS)) {
				url = url.substring(HTTPS.length());
			}
			if (url.endsWith(BEANSTALKAPP_COM)) {
				url = url
						.substring(0, url.length() - BEANSTALKAPP_COM.length());
			}
		}
		return url;
	}

	public void setObject(final String object) {
		_serverContainingModel.setObject(HTTPS + object + BEANSTALKAPP_COM);
	}

	public void detach() {
		_serverContainingModel.detach();
	}

}
