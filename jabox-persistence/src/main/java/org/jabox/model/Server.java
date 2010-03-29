package org.jabox.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.apache.wicket.persistence.domain.BaseEntity;

/**
 * 
 * @author dimitris
 */
@Entity
public class Server extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 4845825512187976712L;
	private String _name;
	private String _url;

	@OneToOne(mappedBy = "server", cascade = CascadeType.ALL)
	public DeployerConfig deployerConfig;

	public DeployerConfig getDeployerConfig() {
		return deployerConfig;
	}

	public void setDeployerConfig(final DeployerConfig newConfig) {
		deployerConfig = newConfig;
		newConfig.setServer(this);
	}

	public void setName(String name) {
		_name = name;
	}

	public String getName() {
		return _name;
	}

	public void setUrl(String url) {
		this._url = url;
	}

	public String getUrl() {
		return _url;
	}
}
