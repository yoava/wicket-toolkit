package org.wtk.model;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModelComparator;

/**
 * @author Yoav Aharoni
 */
public class ReferenceModelComparator implements IModelComparator {
	private static final ReferenceModelComparator INSTANCE = new ReferenceModelComparator();

	@Override
	@SuppressWarnings({"ObjectEquality"})
	public boolean compare(Component component, Object newObject) {
		final Object object = component.getModelObject();
		return object == newObject;
	}

	public static ReferenceModelComparator getInstance() {
		return INSTANCE;
	}
}
