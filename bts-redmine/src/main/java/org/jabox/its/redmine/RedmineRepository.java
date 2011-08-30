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
import java.util.List;

import javax.servlet.http.Cookie;

import net.sourceforge.jwebunit.junit.WebTester;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.jabox.apis.its.ITSConnector;
import org.jabox.apis.scm.SCMConnectorConfig;
import org.jabox.model.DeployerConfig;
import org.jabox.model.Project;
import org.jabox.model.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;

@Service
public class RedmineRepository implements
		ITSConnector<RedmineRepositoryConfig>, Serializable {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(RedmineRepository.class);

	private static final long serialVersionUID = -692328636804684690L;
	public static final String ID = "plugin.its.redmine";

	private final WebConversation _wc = new WebConversation();

	private final WebTester _wt = new WebTester();

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

	public boolean addModule(final Project project,
			final RedmineRepositoryConfig itsConnectorConfig,
			final String module, final String description,
			final String initialOwner) throws SAXException, IOException {
		return true;
	}

	public boolean addProject(final Project project,
			final RedmineRepositoryConfig config) throws IOException,
			SAXException {
		LOGGER.info("Redmine add Project: " + project.getName());

		_wt.gotoPage("/projects/new");
		_wt.setWorkingForm(1);
		_wt.setTextField("project[name]", project.getName());
		_wt.setTextField("project[description]", project.getDescription());
		_wt.setTextField("project[identifier]", getRedmineId(project));
		_wt.submit();
		return true;
	}

	private String getRedmineId(final Project project) {
		return project.getName();
	}

	public boolean addVersion(final Project project,
			final RedmineRepositoryConfig config, final String version)
			throws IOException, SAXException {
		LOGGER.info("Redmine add Version: " + version);

		_wt.gotoPage("/projects/" + getRedmineId(project) + "/versions/new");
		_wt.setWorkingForm(1);
		_wt.setTextField("version[name]", version);
		_wt.submit();
		return true;
	}

	public boolean login(final RedmineRepositoryConfig config)
			throws MalformedURLException, IOException, SAXException {
		LOGGER.info("Redmine Login: " + config.getUsername());

		String url = config.getServer().getUrl();
		return login(url, config.getUsername(), config.getPassword());
	}

	protected boolean login(final String url, final String username,
			final String password) throws MalformedURLException, IOException,
			SAXException {
		_wt.setBaseUrl(url);
		_wt.beginAt("/login");
		_wt.setTextField("username", username);
		_wt.setTextField("password", password);
		_wt.submit();

		if (_wt.getDialog().getPageURL().toExternalForm().endsWith("/my/page")) {
			return true;
		} else {
			return false;
		}
	}

	public DeployerConfig newConfig() {
		return new RedmineRepositoryConfig();
	}

	public Component newEditor(final String id, final IModel<Server> model) {
		return new RedmineRepositoryEditor(id, model);
	}

	public void addRepository(final Project project,
			final RedmineRepositoryConfig config,
			final SCMConnectorConfig scmConfig, final String username,
			final String password) throws MalformedURLException, IOException,
			SAXException {
		LOGGER.info("Redmine add Repository: " + scmConfig.getScmUrl());

		// Check if Repository should be added
		if (!config.isAddRepositoryConfiguration()) {
			return;
		}

		List<Cookie> cookies = (List<Cookie>) _wt.getDialog().getCookies();
		for (Cookie cookie : cookies) {
			_wc.putCookie(cookie.getName(), cookie.getValue());
		}

		PostMethodWebRequest form = new PostMethodWebRequest(config.getServer()
				.getUrl()
				+ "/repositories/edit/" + project.getName());
		form.setParameter("authenticity_token", getAuthenticityToken(_wt
				.getPageSource()));
		form.setParameter("repository_scm", "Subversion");
		form.setParameter("repository[url]", scmConfig.getScmUrl());
		form.setParameter("repository[login]", username);
		form.setParameter("repository[password]", password);
		form.setParameter("commit", "Create");
		_wc.getResponse(form);
	}

	private String getAuthenticityToken(String body) {
		String substr[] = body.split("hidden.* value..");
		String token = substr[1].substring(0, substr[1].indexOf("\""));
		return token;
	}
}
