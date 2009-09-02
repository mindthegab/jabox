package org.jabox.model;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.springframework.stereotype.Service;

@Service
public class FtpDeployerPlugin implements DeployerPlugin {

	public static final String ID = "plugin.deployer.ftp";

	public String getName() {
		return "Ftp Site";
	}

	public String getId() {
		return ID;
	}

	public Component newEditor(String id, IModel model) {
		return new FtpDeployerEditor(id, model);
	}

	public DeployerConfig newConfig() {
		return new FtpDeployerConfig();
	}

	public String deploy(Configuration article) {
		FtpDeployerConfig config = (FtpDeployerConfig) article.deployerConfig;
		return "Deploying article: " + article.getIssueManagementUrl()
				+ " to ftp://" + config.host + ":" + config.port;
	}

}
