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
package org.apache.wicket.persistence.domain;

import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.persistence.provider.GeneralDao;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class BaseEntityDetachableModel<E extends BaseEntity> extends
		LoadableDetachableModel<E> {
	private static final long serialVersionUID = 501303796886245828L;

	@SpringBean(name = "GeneralDao")
	protected GeneralDao generalDao;

	private Long _id;
	private Class<E> _clazz;

	public BaseEntityDetachableModel() {
		InjectorHolder.getInjector().inject(this);
	}

	@SuppressWarnings("unchecked")
	public BaseEntityDetachableModel(final E baseEntity) {
		this();
		_id = baseEntity.getId();
		_clazz = (Class<E>) baseEntity.getClass();

	}

	@SuppressWarnings("unchecked")
	public void setBaseEntityDetachableModel(BaseEntity baseEntity) {
		_id = baseEntity.getId();
		_clazz = (Class<E>) baseEntity.getClass();
	}

	@Override
	protected E load() {
		if (_clazz != null) {

			return generalDao.findEntity(_id, _clazz);
		} else {
			return null;
		}
	}

}
