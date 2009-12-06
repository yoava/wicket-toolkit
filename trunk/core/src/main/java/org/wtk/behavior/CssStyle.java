package org.wtk.behavior;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * @author Yoav Aharoni
 */
public class CssStyle extends AttributeAppender {
	public CssStyle(final String attribute, final String value) {
		super("style", true, new Model(attribute + ":" + value), ";");
	}

	public CssStyle(final String attribute, final IModel model) {
		super("style", true, new StyleModel(attribute, model), ";");
	}

	private static class StyleModel extends AbstractReadOnlyModel {
		private final String attribute;
		private final IModel model;

		private StyleModel(String attribute, IModel model) {
			this.attribute = attribute;
			this.model = model;
		}

		@Override
		public Object getObject() {
			return attribute + ":" + model.getObject();
		}
	}
}
