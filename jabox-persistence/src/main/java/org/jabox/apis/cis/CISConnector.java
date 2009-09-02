package org.jabox.apis.cis;

import java.io.IOException;
import java.net.MalformedURLException;

import org.jabox.model.Project;
import org.xml.sax.SAXException;

public interface CISConnector {
	public boolean login(String username, String password)
			throws MalformedURLException, IOException, SAXException;

	public boolean addProject(final Project project) throws IOException,
			SAXException;
}
