package org.jabox.cis.hudson;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.jabox.apis.cis.CISConnector;
import org.jabox.model.Project;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

@Service
public class HudsonConnector implements CISConnector {

	@Override
	public String toString() {
		return "Hudson Plugin";
	}

	/**
	 * Implementation inspired by groovy code here:
	 * http://wiki.hudson-ci.org/display/HUDSON/Authenticating+scripted+clients
	 * 
	 */
	public boolean addProject(Project project) throws IOException, SAXException {
		// XXX This needs to be configured.
		String hudsonHost = "http://localhost:9090/hudson/";

		HttpClient client = new HttpClient();
		String uri = hudsonHost + "createItem?name=" + project.getName();
		PostMethod post = new PostMethod(uri);
		post.setRequestHeader("Content-type", "text/xml; charset=UTF-8");
		post.setDoAuthentication(true);

		InputStream is = HudsonConnector.class
				.getResourceAsStream("config.xml");

		post.setRequestBody(is);
		try {
			int result = client.executeMethod(post);
			System.out.println("Return code: " + result);
			for (Header header : post.getResponseHeaders()) {
				System.out.println(header.toString().trim());
			}
			System.out.println(post.getResponseBodyAsString());
		} finally {
			post.releaseConnection();
		}
		
		// Trigger the Hudson build
		PostMethod triggerBuild = new PostMethod(hudsonHost + "/job/"
				+ project.getName() + "/build");
		client.executeMethod(triggerBuild);
		return true;
	}

	public boolean login(String username, String password)
			throws MalformedURLException, IOException, SAXException {
		return true;
	}

}
