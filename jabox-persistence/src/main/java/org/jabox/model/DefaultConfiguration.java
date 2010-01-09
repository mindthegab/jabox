package org.jabox.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.persistence.domain.BaseEntity;
import org.jabox.apis.ConnectorConfig;
import org.jabox.apis.bts.ITSConnectorConfig;
import org.jabox.apis.cis.CISConnectorConfig;
import org.jabox.apis.rms.RMSConnectorConfig;
import org.jabox.apis.scm.SCMConnectorConfig;

@Entity
public class DefaultConfiguration extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 970298449487373906L;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private DeployerConfig _its;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private DeployerConfig _cis;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private DeployerConfig _rms;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private DeployerConfig _scm;

	public void setIts(DeployerConfig its) {
		_its = its;
	}

	public DeployerConfig getIts() {
		return _its;
	}

	public String isDefault(ListItem<ConnectorConfig> item) {
		System.out.println("ITEM: " + item);
		System.out.println("ITS: " + _its);
		if (item == null || _its == null) {
			return "false";
		}

		if (!item.getModelObject().getId().equals(_its.getId())) {
			System.out.println("IS DEFAULT: " + item.getId() + " :  "
					+ _its.getId());
			return "false";
		}

		return "true";
	}

	public void setDefault(ConnectorConfig config) {
		System.out.println("-----> " + config.getId());

		if (ITSConnectorConfig.class.isAssignableFrom(config.getClass())) {
			DeployerConfig its = (DeployerConfig) config;
			_its = its;
		} else if (config instanceof CISConnectorConfig) {
			DeployerConfig cis = (DeployerConfig) config;
			_cis = cis;
		} else if (config instanceof RMSConnectorConfig) {
			DeployerConfig rms = (DeployerConfig) config;
			_rms = rms;
		} else if (config instanceof SCMConnectorConfig) {
			DeployerConfig scm = (DeployerConfig) config;
			_scm = scm;
		}
	}

	public void setCis(DeployerConfig cis) {
		_cis = cis;
	}

	public DeployerConfig getCis() {
		return _cis;
	}

	public void setScm(DeployerConfig scm) {
		_scm = scm;
	}

	public DeployerConfig getScm() {
		return _scm;
	}

	public void setRms(DeployerConfig rms) {
		_rms = rms;
	}

	public DeployerConfig getRms() {
		return _rms;
	}
}
