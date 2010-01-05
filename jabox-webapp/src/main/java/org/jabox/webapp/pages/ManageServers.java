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
package org.jabox.webapp.pages;

import java.util.List;

import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.persistence.domain.BaseEntity;
import org.apache.wicket.persistence.provider.GeneralDao;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.jabox.apis.Connector;
import org.jabox.apis.ConnectorConfig;
import org.jabox.apis.Manager;
import org.jabox.apis.scm.SCMConnector;
import org.jabox.webapp.borders.MiddlePanel;
import org.jabox.webapp.utils.SCMConnectorList;

/**
 * Homepage
 */
@AuthorizeInstantiation("ADMIN")
public class ManageServers extends MiddlePanel {

	private static final long serialVersionUID = 1L;

	@SpringBean
	protected GeneralDao _generalDao;

	@SpringBean
	protected Manager<Connector> _manager;

	public ManageServers() {
		Class<? extends Connector> connector = SCMConnector.class;
		List<Connector> connectors = _manager.getConnectors(connector);
		List<ConnectorConfig> entities = _generalDao
				.getEntities(ConnectorConfig.class);
		System.out.println("connectors: " + connector.getName() + ":"
				+ connectors);

		Form<BaseEntity> form = new Form<BaseEntity>("deleteForm");
		form.add(new SCMConnectorList("projects", entities));
		add(form);
	}
}
