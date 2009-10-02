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
	private String _scmConnector;
	private String _btsConnector;
	private String _rmsConnector;

	private String _cisConnector;
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

	public void setSCMConnector(final String scmConnector) {
		_scmConnector = scmConnector;
	}

	public String getSCMConnector() {
		return _scmConnector;
	}

	public void setBTSConnector(final String btsConnector) {
		_btsConnector = btsConnector;
	}

	public String getBTSConnector() {
		return _btsConnector;
	}

	public void setCISConnector(final String cisConnector) {
		_cisConnector = cisConnector;
	}

	public String getCISConnector() {
		return _cisConnector;
	}

	public void setDeployerConfig(final DeployerConfig newConfig) {
		deployerConfig = newConfig;
		newConfig.configuration = this;
	}

	public String getRMSConnector() {
		return _rmsConnector;
	}

	public void setRMSConnector(final String _rmsConnector) {
		this._rmsConnector = _rmsConnector;
	}

}
