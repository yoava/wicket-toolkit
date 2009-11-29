package org.wtk.behavior;

import org.apache.wicket.Component;

/**
 * @author Yoav Aharoni
 */
public class BoundHeadPackage extends HeadResource {
    private Component component;

    public BoundHeadPackage(Class<?> scope) {
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
