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
	private static final String TRUE = "true";

	private static final String FALSE = "false";

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
		if (item == null || _its == null) {
			return FALSE;
		}

		Long modelObjId = item.getModelObject().getId();
		if (modelObjId.equals(_its.getId())) {
			return TRUE;
		} else if (modelObjId.equals(_cis.getId())) {
			return TRUE;
		} else if (modelObjId.equals(_rms.getId())) {
			return TRUE;
		} else if (modelObjId.equals(_scm.getId())) {
			return TRUE;
		}

		return FALSE;
	}

	public void setDefault(ConnectorConfig config) {
		if (ITSConnectorConfig.class.isAssignableFrom(config.getClass())) {
			DeployerConfig its = (DeployerConfig) config;
			_its = its;
		} else if (CISConnectorConfig.class.isAssignableFrom(config.getClass())) {
			DeployerConfig cis = (DeployerConfig) config;
			_cis = cis;
		} else if (RMSConnectorConfig.class.isAssignableFrom(config.getClass())) {
			DeployerConfig rms = (DeployerConfig) config;
			_rms = rms;
		} else if (SCMConnectorConfig.class.isAssignableFrom(config.getClass())) {
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
