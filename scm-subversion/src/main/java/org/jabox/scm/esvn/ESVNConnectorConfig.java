package org.jabox.scm.esvn;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.jabox.model.DeployerConfig;
import org.jabox.scm.svn.ISVNConnectorConfig;
import org.jabox.scm.svn.SubversionRepository;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;

@Entity
@DiscriminatorValue(ESVNConnector.ID)
public class ESVNConnectorConfig extends DeployerConfig implements
		ISVNConnectorConfig {
	private static final long serialVersionUID = 6542402958304063770L;

	public ESVNConnectorConfig() {
		pluginId = "plugin.scm.esvn";
	}

	public SVNURL getSvnDir() throws SVNException {
		return SVNURL.fromFile(SubversionRepository.getSubversionBaseDir());
	}

	/**
	 * We don't need username on embedded.
	 */
	public String getUsername() {
		return "";
	}

	/**
	 * We don't need password on embedded.
	 */
	public String getPassword() {
		return "";
	}

}
