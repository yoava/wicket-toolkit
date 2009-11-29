package org.wtk.component.support.plugin;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.RequestCycle;

/**
 * @author Yoav Aharoni
 */
public class HierarchyPluginManagerLocator implements IPluginManagerLocator {
    @Override
    public IPluginManager locate() {
        return locate(RequestCycle.get().getResponsePage());
    }

    @Override
    public IPluginManager locate(Page page) {
        Object result = page.visitChildren(IPluginManager.class, new Component.IVisitor() {
            @Override
            public Object component(Component component) {
                return component;
            }
        });
        if (result instanceof IPluginManager) {
            return (IPluginManager) result;
        }
        return null;
    }
}
