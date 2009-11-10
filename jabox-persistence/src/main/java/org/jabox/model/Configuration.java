/*
 * The MIT License
 *
 * Copyright (c) 2009 Dimitris Kapanidis
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
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
