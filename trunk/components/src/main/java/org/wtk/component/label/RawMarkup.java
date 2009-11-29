package org.wtk.component.label;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

/**
 * @author Yoav Aharoni
 */
public class RawMarkup extends Label {
	public RawMarkup(String id, String label) {
		super(id, label);
	}

	public RawMarkup(String id, IModel model) {
		super(id, model);
	}

	{
		setEscapeModelStrings(false);
	}
}
