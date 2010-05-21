package org.wtk.component.support.plugin;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.wtk.application.CurrentPageSupport;

/**
 * @author Yoav Aharoni
 */
public class HierarchyPluginManagerLocator implements IPluginManagerLocator {
	@Override
	public IPluginManager locate() {
		final Page currentPage = CurrentPageSupport.getCurrentPage();
		if (currentPage == null) {
			throw new RuntimeException("ResponsePage is null, you may locate PluginManager using PluginManager.get(page).");
		}
		return locate(currentPage);
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
