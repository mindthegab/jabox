package org.jabox.model;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.springframework.stereotype.Service;

@Service
public class HttpPostDeployerPlugin implements DeployerPlugin {
	public static final String ID = "plugin.deployer.httppost";

	public String getName() {
		return "Http Post";
	}

	public String getId() {
		return ID;
	}

	public Component newEditor(String id, IModel<Configuration> model) {
		return new HttpPostDeployerEditor(id, model);
	}

	public DeployerConfig newConfig() {
		return new HttpPostDeployerConfig();
	}

	public String deploy(Configuration article) {
		HttpPostDeployerConfig config = (HttpPostDeployerConfig) article.deployerConfig;
		return "Deploying article: " + article.getIssueManagementUrl()
				+ " to http://" + config.url;
	}
}
