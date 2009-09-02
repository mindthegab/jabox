package org.jabox.cis.hudson;

import java.io.IOException;
import java.net.MalformedURLException;

import org.jabox.apis.cis.CISConnector;
import org.jabox.model.Project;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

@Service
public class HudsonConnector implements CISConnector {

	@Override
	public String toString() {
		return "Hudson Plugin";
	}

	public boolean addProject(Project project) throws IOException, SAXException {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean login(String username, String password)
			throws MalformedURLException, IOException, SAXException {
		// TODO Auto-generated method stub
		return true;
	}

}
