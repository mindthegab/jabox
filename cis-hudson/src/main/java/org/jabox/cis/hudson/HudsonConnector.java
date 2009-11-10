/*
 * The MIT License
 *
 * Copyright (c) 2009 Dimitris Kapanidis
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.jabox.cis.hudson;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.MalformedURLException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.wicket.util.io.IOUtils;
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

		String body = parseInputStream(is, project);
		post.setRequestBody(body);
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

	private String parseInputStream(InputStream is, Project project)
			throws IOException {
		StringWriter writer = new StringWriter();
		IOUtils.copy(is, writer);
		String theString = writer.toString();

		String replace = theString.replace("${project.scmURL}", project
				.getScmUrl());
		replace = replace.replace("${project.issueURL}", "http://localhost/redmine/issues/show/$1");
		replace = replace.replace("${goals}", "clean checkstyle:checkstyle findbugs:findbugs pmd:cpd deploy");
		return replace;
	}

	public boolean login(String username, String password)
			throws MalformedURLException, IOException, SAXException {
		return true;
	}

}
