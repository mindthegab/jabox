package org.jabox.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.apache.wicket.persistence.domain.BaseEntity;

@Entity
public class Configuration extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -5816897164349338291L;
	private String _issueManagementUrl;
	private String _defaultSCMConnector;
	private String _defaultBTSConnector;
	private String _defaultRMSConnector;

	private String _defaultCISConnector;
	@OneToOne(mappedBy = "configuration", cascade = CascadeType.ALL)
	public DeployerConfig deployerConfig;

	public DeployerConfig getDeployerConfig() {
		return deployerConfig;
	}

	public void setIssueManagementUrl(final String issueManagementUrl) {
		_issueManagementUrl = issueManagementUrl;
	}

	public String getIssueManagementUrl() {
		return _issueManagementUrl;
	}

	public void setDefaultSCMConnector(final String defaultSCMConnector) {
		_defaultSCMConnector = defaultSCMConnector;
	}

	public String getDefaultSCMConnector() {
		return _defaultSCMConnector;
	}

	public void setDefaultBTSConnector(final String defaultBTSConnector) {
		_defaultBTSConnector = defaultBTSConnector;
	}

	public String getDefaultBTSConnector() {
		return _defaultBTSConnector;
	}

	public void setDefaultCISConnector(final String _defaultCISConnector) {
		this._defaultCISConnector = _defaultCISConnector;
	}

	public String getDefaultCISConnector() {
		return _defaultCISConnector;
	}

	public void setDeployerConfig(final DeployerConfig newConfig) {
		deployerConfig = newConfig;
		newConfig.configuration = this;
	}

	public String getDefaultRMSConnector() {
		return _defaultRMSConnector;
	}

	public void setDefaultRMSConnector(final String _defaultRMSConnector) {
		this._defaultRMSConnector = _defaultRMSConnector;
	}

}
