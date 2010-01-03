package org.wtk.component.support.plugin;

import org.apache.wicket.markup.html.panel.Panel;
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

	@SuppressWarnings({"unchecked"})
	public T getPluginModelObject() {
		return (T) getModelObject();
	}

	public void setPluginModelObject(T object) {
		setModelObject(object);
	}

	protected boolean isHiddenStyle() {
		return true;
	}
}
