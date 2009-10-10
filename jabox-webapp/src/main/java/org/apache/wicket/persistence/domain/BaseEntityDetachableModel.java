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
