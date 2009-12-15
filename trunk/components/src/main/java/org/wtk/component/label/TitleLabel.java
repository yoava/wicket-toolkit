package org.wtk.component.label;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.PropertyModel;
import org.wtk.api.Titled;

import static org.apache.commons.lang.StringUtils.isEmpty;

/**
 * @author Yoav Aharoni
 */
public class TitleLabel extends Label {
	public TitleLabel(String id, final Titled titled) {
		super(id, new PropertyModel(titled, "title"));
	}

	@Override
	public boolean isVisible() {
		return super.isVisible() && !isEmpty(getModelObjectAsString());
	}
}
