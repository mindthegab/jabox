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
package org.jabox.bts.jtrac;

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
public class JtracRepository implements BTSConnector, Serializable {
	private static final long serialVersionUID = 8131183843391948936L;
	public static final String ID = "plugin.its.jtrac";

	private String _url;
	private final WebConversation _wc;

	public String getName() {
		return "Jtrac Plugin";
	}

	public String getId() {
		return ID;
	}

	@Override
	public String toString() {
		return getName();
	}

	public JtracRepository() {
		_wc = new WebConversation();
	}

	public void setUrl(final String url) {
		_url = url;
	}

	public boolean login(String username, String password)
			throws MalformedURLException, IOException, SAXException {
		WebRequest req = new GetMethodWebRequest(_url);
		WebResponse resp = _wc.getResponse(req);
		WebForm form = resp.getForms()[0]; // select the first form in the page
		form.setParameter("j_username", username);
		form.setParameter("j_password", password);
		resp = form.submit();

		// TODO check if not logged in.
		return true;
	}

	public boolean addProject(final Project project) throws IOException,
			SAXException {
		WebRequest req = new GetMethodWebRequest(_url
				+ "/editproducts.cgi?action=add");
		WebResponse resp = _wc.getResponse(req);
		WebForm form = resp.getForms()[0];

		form.setParameter("product", project.getName());
		form.setParameter("description", project.getDescription());
		form.submit();
		return true;
	}

	public boolean addModule(final Project project, final String module,
			final String description, final String initialOwner)
			throws SAXException, IOException {

		WebRequest req = new GetMethodWebRequest(
				"http://localhost/cgi-bin/bugzilla/editcomponents.cgi?action=add&product="
						+ project.getName());
		WebResponse resp = _wc.getResponse(req);
		WebForm form = resp.getForms()[0];

		form.setParameter("component", module);
		form.setParameter("description", description);
		form.setParameter("initialowner", initialOwner);
		form.submit();
		return true;
	}

	public boolean addVersion(Project project, String version)
			throws IOException, SAXException {

		WebRequest req = new GetMethodWebRequest(
				"http://localhost/cgi-bin/bugzilla/editversions.cgi?action=add&product="
						+ project.getName());
		WebResponse resp = _wc.getResponse(req);
		WebForm form = resp.getForms()[0];

		form.setParameter("version", version);
		form.submit();
		return true;
	}

	public DeployerConfig newConfig() {
		return new JtracRepositoryConfig();
	}
 
	public Component newEditor(String id, IModel<Server> model) {
		return new JtracRepositoryEditor(id, model);
	}
}
