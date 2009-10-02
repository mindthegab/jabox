package org.jabox.apis.bts;

import java.io.IOException;
import java.net.MalformedURLException;

import org.jabox.apis.Connector;
import org.jabox.model.Project;
import org.xml.sax.SAXException;

public interface BTSConnector extends Connector {

	public boolean login(String username, String password)
			throws MalformedURLException, IOException, SAXException;

	public void setUrl(final String url);

	public boolean addProject(final Project project) throws IOException,
			SAXException;

	public boolean addModule(final Project project, final String module,
			final String description, final String initialOwner)
			throws SAXException, IOException;

	public boolean addVersion(Project project, String version)
			throws IOException, SAXException;
}
