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
package org.jabox.webapp.utils;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IFormSubmittingComponent;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class TransactionalForm<T> extends Form<T> {
	private static final long serialVersionUID = -3182000832570499475L;
	@SpringBean
	private PlatformTransactionManager manager;

	public TransactionalForm(final String id) {
		super(id);
	}

	public TransactionalForm(final String id, final IModel<T> model) {
		super(id, model);
	}

	@Override
	public void process(final IFormSubmittingComponent submittingComponent) {
		TransactionStatus txn = manager
				.getTransaction(new DefaultTransactionDefinition());
		try {
			super.process(submittingComponent);
			if (!hasError()) {
				manager.commit(txn);
				txn = null;
			}
		} finally {
			if (txn != null) {
				manager.rollback(txn);
			}
		}
	}

}
