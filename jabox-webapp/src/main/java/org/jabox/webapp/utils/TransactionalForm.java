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

	public TransactionalForm(String id) {
		super(id);
	}

	public TransactionalForm(String id, IModel<T> model) {
		super(id, model);
	}

	@Override
	public void process(IFormSubmittingComponent submittingComponent) {
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
