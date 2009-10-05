package org.jabox.webapp.utils;

import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.model.AbstractReadOnlyModel;

public class EvenOddRow<T> extends AbstractReadOnlyModel<String> {
	private static final long serialVersionUID = 1L;
	private final ListItem<T> _listItem;

	public EvenOddRow(ListItem<T> listItem) {
		_listItem = listItem;
	}

	public String getObject() {
		return (_listItem.getIndex() % 2 == 1) ? "even" : "odd";
	}
}