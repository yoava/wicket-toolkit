package org.wtk.behavior.head;

import org.apache.wicket.Component;

/**
 * <b>NOTE:</b> Must not be static.
 *
 * @author Yoav Aharoni
 */
public class ComponentAwareHeadResource extends HeadResource {
	private Component component;

	public ComponentAwareHeadResource(Class<?> scope) {
		super(scope);
	}

	@Override
	public void bind(Component component) {
		super.bind(component);
		this.component = component;
	}

	public Component getComponent() {
		return component;
	}

	public void setComponent(Component component) {
		this.component = component;
	}
}
