package org.wtk.util;

import org.apache.wicket.model.IModel;

/**
 * @author Yoav Aharoni
 */
public class ModelUtils {
	public static String toString(IModel model) {
		if (model == null) {
			return null;
		}
		final Object object = model.getObject();
		if (object == null) {
			return null;
		}
		return object.toString();
	}
}
