package org.wtk.behavior.css;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * @author Yoav Aharoni
 */
public class CssStyle extends AttributeAppender {
	public CssStyle(final String attribute, final String value) {
		super("style", true, new Model<String>(attribute + ":" + value), ";");
	}

	public CssStyle(final String attribute, final IModel model) {
		super("style", true, new StyleModel(attribute, model), ";");
	}

	private static class StyleModel extends AbstractReadOnlyModel {
		private final IModel model;
		private final String attribute;

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
