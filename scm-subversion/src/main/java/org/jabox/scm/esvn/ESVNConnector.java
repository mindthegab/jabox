package org.jabox.scm.esvn;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.jabox.model.DeployerConfig;
import org.jabox.model.Server;
import org.jabox.scm.svn.SVNConnector;
import org.springframework.stereotype.Service;

@Service
/**
 * Embedded Subversion Connector.
 * <p>
 *
 * The "Service" annotation is injecting it to the list of the Plugins.
 */
public class ESVNConnector extends SVNConnector {
	private static final long serialVersionUID = -8772470110891207618L;

	/**
	 * Unique Plugin ID.
	 * <p/>
	 * TODO: This can be replaced by the unique package name.
	 */
	public static final String ID = "plugin.scm.esvn";

	/**
	 * The Displayable name of the Plugin.
	 * <p/>
	 * TODO: This can be replaced by i18n text in plugin.property.
	 */
	@Override
	public String getName() {
		return "Embedded Subversion";
	}

	/**
	 * Retrieves the Unique Plugin ID.
	 * <p/>
	 * TODO: This can be replaced by the unique package name.
	 */
	@Override
	public String getId() {
		return "plugin.scm.esvn";
	}

	/**
	 * Configuration Factory.
	 * <p/>
	 * TODO: To be decoupled.
	 */
	@Override
	public DeployerConfig newConfig() {
		return new ESVNConnectorConfig();
	}

	/**
	 * Editor Factory.
	 * <p/>
	 * TODO: To be decoupled.
	 */
	@Override
	public Component newEditor(String id, IModel<Server> model) {
		return new ESVNConnectorEditor(id, model);
	}
}
