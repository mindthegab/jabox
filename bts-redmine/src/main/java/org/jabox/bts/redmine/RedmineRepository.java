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
package org.jabox.bts.redmine;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.jabox.apis.bts.BTSConnector;
import org.jabox.model.DeployerConfig;
import org.jabox.model.Project;
import org.jabox.model.Server;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

@Service
public class RedmineRepository implements BTSConnector, Serializable {
	private static final long serialVersionUID = -692328636804684690L;
	public static final String ID = "plugin.its.redmine";

	private String _url;
	private final WebConversation _wc;

	public String getName() {
		return "Redmine Plugin";
	}

	public String getId() {
		return ID;
	}

	@Override
	public String toString() {
		return getName();
	}

	/**
	 * @param url
	 *            Redmine URL.
	 */
	public RedmineRepository() {
		_wc = new WebConversation();
	}

	public void setUrl(String url) {
		_url = url;
	}

	public boolean addModule(Project project, String module,
			String description, String initialOwner) throws SAXException,
			IOException {
		return true;
	}

	public boolean addProject(Project project) throws IOException, SAXException {
		WebRequest req = new GetMethodWebRequest(_url + "/projects/add");
		WebResponse resp = _wc.getResponse(req);
		WebForm form = resp.getForms()[1];
		form.getParameterNames();
		form.setParameter("project[name]", project.getName());
		form.setParameter("project[description]", project.getDescription());
		form.setParameter("project[identifier]", "id-" + project.getId());
		form.submit();
		return true;
	}

	public boolean addVersion(Project project, String version)
			throws IOException, SAXException {

		WebRequest req = new GetMethodWebRequest(_url
				+ "/projects/add_version/id-" + project.getId());

		WebResponse resp = _wc.getResponse(req);
		WebForm form = resp.getForms()[1];

		form.setParameter("version[name]", version);
		form.submit();
		return true;
	}

	public boolean login(String username, String password)
			throws MalformedURLException, IOException, SAXException {
		WebRequest req = new GetMethodWebRequest(_url + "/login");
		WebResponse resp = _wc.getResponse(req);
		WebForm form = resp.getForms()[1]; // select the second form in the
		// page
		form.setParameter("username", username);
		form.setParameter("password", password);
		resp = form.submit();

		if (resp.getURL().getPath().endsWith("/my/page")) {
			return true;
		}
		return false;
	}

	public DeployerConfig newConfig() {
		return new RedmineRepositoryConfig();
	}

	public Component newEditor(String id, IModel<Server> model) {
		return new RedmineRepositoryEditor(id, model);
	}

}
