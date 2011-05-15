package org.wtk.behavior;

import org.apache.wicket.behavior.AbstractBehavior;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.io.Serializable;

/**
 * @author Yoav Aharoni
 */
public class BaseHeadBehavior<T extends Serializable> extends AbstractBehavior {
	private IModel model;

	public BaseHeadBehavior(IModel model) {
		this.model = model;
	}

	public BaseHeadBehavior(T value) {
		this(new Model(value));
	}

	@SuppressWarnings({"unchecked"})
	public T getModelObject() {
		return model == null ? null : (T) model.getObject();
	}

	public IModel getModel() {
		return model;
	}
}
