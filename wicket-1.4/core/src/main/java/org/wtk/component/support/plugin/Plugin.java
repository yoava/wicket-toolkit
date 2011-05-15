package org.wtk.component.support.plugin;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.wtk.behavior.css.HiddenStyle;

import static org.wtk.component.list.ComponentListView.ITEM_ID;

/**
 * @author Yoav Aharoni
 */
public class Plugin<T> extends Panel {
	public Plugin() {
		super(ITEM_ID);
		if (isHiddenStyle()) {
			add(new HiddenStyle());
		}
	}

	public T getPluginModelObject() {
		return getModelObject();
	}

	public void setPluginModelObject(T object) {
		setDefaultModelObject(object);
	}

	public void setModel(IModel<T> model) {
		setDefaultModel(model);
	}

	@SuppressWarnings({"unchecked"})
	public IModel<T> getModel() {
		return (IModel<T>) getDefaultModel();
	}

	@SuppressWarnings({"unchecked"})
	public T getModelObject() {
		return (T) getDefaultModelObject();
	}

	public void setModelObject(T object) {
		setDefaultModelObject(object);

	}

	protected boolean isHiddenStyle() {
		return true;
	}
}
