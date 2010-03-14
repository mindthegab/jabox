package org.jabox.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.persistence.domain.BaseEntity;
import org.jabox.apis.ConnectorConfig;
import org.jabox.apis.cis.CISConnectorConfig;
import org.jabox.apis.its.ITSConnectorConfig;
import org.jabox.apis.rms.RMSConnectorConfig;
import org.jabox.apis.scm.SCMConnectorConfig;

@Entity
public class DefaultConfiguration extends BaseEntity implements Serializable {
	public static final String TRUE = "true";

	public static final String FALSE = "false";

	private static final long serialVersionUID = 970298449487373906L;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private DeployerConfig _its;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private DeployerConfig _cis;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private DeployerConfig _rms;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private DeployerConfig _scm;

	public String isDefault(ConnectorConfig item) {
		if (item == null) {
			return FALSE;
		}

		Long modelObjId = item.getId();
		if (_its != null && modelObjId.equals(_its.getId())) {
			return TRUE;
		} else if (_cis != null && modelObjId.equals(_cis.getId())) {
			return TRUE;
		} else if (_rms != null && modelObjId.equals(_rms.getId())) {
			return TRUE;
		} else if (_scm != null && modelObjId.equals(_scm.getId())) {
			return TRUE;
		}

		return FALSE;
	}

	public String isDefault(ListItem<ConnectorConfig> listItem) {
		if (listItem == null) {
			return FALSE;
		}
		return isDefault(listItem.getModelObject());
	}

	public void setDefault(ConnectorConfig config) {
		if (ITSConnectorConfig.class.isAssignableFrom(config.getClass())) {
			_its = (DeployerConfig) config;
		} else if (CISConnectorConfig.class.isAssignableFrom(config.getClass())) {
			_cis = (DeployerConfig) config;
		} else if (RMSConnectorConfig.class.isAssignableFrom(config.getClass())) {
			_rms = (DeployerConfig) config;
		} else if (SCMConnectorConfig.class.isAssignableFrom(config.getClass())) {
			_scm = (DeployerConfig) config;
		}
	}

	public ITSConnectorConfig getIts() {
		return (ITSConnectorConfig) _its;
	}

	public CISConnectorConfig getCis() {
		return (CISConnectorConfig) _cis;
	}

	public SCMConnectorConfig getScm() {
		return (SCMConnectorConfig) _scm;
	}

	public RMSConnectorConfig getRms() {
		return (RMSConnectorConfig) _rms;
	}
}
