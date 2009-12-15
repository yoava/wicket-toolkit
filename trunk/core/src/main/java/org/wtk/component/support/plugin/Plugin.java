package org.wtk.component.support.plugin;

import org.apache.wicket.markup.html.panel.Panel;
import org.wtk.behavior.css.HiddenStyle;

import static org.wtk.component.list.ComponentListView.ITEM_ID;

/**
 * @author Yoav Aharoni
 */
public class Plugin extends Panel {
	public Plugin() {
		super(ITEM_ID);
		if (isHiddenStyle()) {
			add(new HiddenStyle());
		}
	}

	protected boolean isHiddenStyle() {
		return true;
	}
}
