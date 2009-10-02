package org.jabox.apis.cis;

import java.io.IOException;
import java.net.MalformedURLException;

import org.jabox.apis.Connector;
import org.jabox.model.Project;
import org.xml.sax.SAXException;

public interface CISConnector extends Connector {
	public boolean login(String username, String password)
			throws MalformedURLException, IOException, SAXException;

	public boolean addProject(final Project project) throws IOException,
			SAXException;
}
