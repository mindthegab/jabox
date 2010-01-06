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

import org.apache.wicket.Page;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.form.ImageButton;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.persistence.provider.GeneralDao;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.jabox.apis.IBaseEntity;

public final class DeleteEntityButton<T extends IBaseEntity> extends
		ImageButton {
	private static final ResourceReference DELETE_IMG = new ResourceReference(
			DeleteEntityButton.class, "delete.jpg");
	private static final long serialVersionUID = 1L;
	private final T _item;
	private Class<? extends Page> _responsePage;

	public DeleteEntityButton(final String id, final T item,
			Class<? extends Page> responsePage) {
		super(id, DELETE_IMG);
		_item = item;
		_responsePage = responsePage;
	}

	public DeleteEntityButton(final String id, final ListItem<T> item,
			Class<? extends Page> responsePage) {
		this(id, item.getModelObject(), responsePage);
	}

	@SpringBean(name = "GeneralDao")
	protected GeneralDao generalDao;

	/**
	 * Delete from persistent storage, commit transaction.
	 */
	public void onSubmit() {
		generalDao.deleteEntity(_item);
		setResponsePage(_responsePage);
	}
}
