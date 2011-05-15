package org.wtk.jquery.ui;

import org.apache.wicket.model.IModel;
import org.wtk.component.panel.base.BasePanel;

import java.io.Serializable;

/**
 * @author Yoav Aharoni
 */
public abstract class BaseJQueryPanel<T extends Serializable> extends BasePanel<T> {
	protected BaseJQueryPanel() {
	}

	protected BaseJQueryPanel(IModel model) {
		super(model);
	}

	protected BaseJQueryPanel(String id) {
		super(id);
	}

	protected BaseJQueryPanel(String id, T modelObject) {
		super(id, modelObject);
	}

	protected BaseJQueryPanel(String id, IModel model) {
		super(id, model);
	}
}
