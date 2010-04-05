/*
 * Jabox Open Source Version
 * Copyright (C) 2009-2010 Dimitris Kapanidis                                                                                                                          
 * 
 * This file is part of Jabox
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 */
package org.jabox.its.redmine;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.jabox.apis.its.ITSConnector;
import org.jabox.apis.its.ITSConnectorConfig;
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
public class RedmineRepository implements ITSConnector, Serializable {
	private static final long serialVersionUID = -692328636804684690L;
	public static final String ID = "plugin.its.redmine";

	private final WebConversation _wc;

	public String getName() {
		return "Redmine";
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

	public boolean addModule(final Project project,
			final ITSConnectorConfig itsConnectorConfig, final String module,
			final String description, final String initialOwner)
			throws SAXException, IOException {
		return true;
	}

	public boolean addProject(final Project project,
			final ITSConnectorConfig config) throws IOException, SAXException {
		WebRequest req = new GetMethodWebRequest(config.getServer().getUrl()
				+ "/projects/add");
		WebResponse resp = _wc.getResponse(req);
		WebForm form = resp.getForms()[1];
		form.getParameterNames();
		form.setParameter("project[name]", project.getName());
		form.setParameter("project[description]", project.getDescription());
		form.setParameter("project[identifier]", getRedmineId(project));
		resp = form.submit();
		if (resp.getURL().getPath().endsWith("/admin/projects")) {
			return true;
		}
		return false;
	}

	private String getRedmineId(final Project project) {
		return project.getName();
	}

	public boolean addVersion(final Project project,
			final ITSConnectorConfig config, final String version)
			throws IOException, SAXException {

		WebRequest req = new GetMethodWebRequest(config.getServer().getUrl()
				+ "/projects/add_version/" + getRedmineId(project));

		WebResponse resp = _wc.getResponse(req);
		WebForm form = resp.getForms()[1];

		form.setParameter("version[name]", version);
		form.submit();
		return true;
	}

	public boolean login(final String username, final String password,
			final ITSConnectorConfig config) throws MalformedURLException,
			IOException, SAXException {

		WebRequest req = new GetMethodWebRequest(config.getServer().getUrl()
				+ "/login");
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

	public Component newEditor(final String id, final IModel<Server> model) {
		return new RedmineRepositoryEditor(id, model);
	}

}
