package org.jabox.webapp.utils;

import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.model.AbstractReadOnlyModel;

public class EvenOddRow extends AbstractReadOnlyModel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private final ListItem _listItem;

		public EvenOddRow(ListItem listItem) {
			_listItem = listItem;
		}

		public Object getObject() {
			return (_listItem.getIndex() % 2 == 1) ? "even" : "odd";
		}
	}