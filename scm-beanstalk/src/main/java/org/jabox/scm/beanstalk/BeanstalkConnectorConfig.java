package org.jabox.scm.beanstalk;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.apache.wicket.model.IModel;
import org.jabox.model.DeployerConfig;
import org.jabox.scm.svn.ISVNConnectorConfig;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;

@Entity
@DiscriminatorValue(BeanstalkConnector.ID)
public class BeanstalkConnectorConfig extends DeployerConfig implements
		ISVNConnectorConfig {
	private static final String SVN_BEANSTALKAPP_COM = ".svn.beanstalkapp.com/";

	private static final String HTTP = "http://";

	private static final long serialVersionUID = -830757629457448866L;

	public BeanstalkConnectorConfig() {
		pluginId = BeanstalkConnector.ID;
	}

	public String username;

	public String password;

	public SVNURL getSvnDir() throws SVNException {
		return SVNURL.parseURIEncoded(getScmUrl());
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getAccountURL() {
		IModel<String> baModel = new BeanstalkAccountURLModel(getServer()
				.getUrl());
		return baModel.getObject();
	}

	public String getScmUrl() {
		// XXX fixme
		String projectName = "jabox";
		String scmURL = HTTP + getAccountURL() + SVN_BEANSTALKAPP_COM + projectName;
		return scmURL;
	}
}
