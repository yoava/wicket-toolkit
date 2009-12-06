package org.wtk.component.support.plugin;

import org.apache.wicket.Page;
import org.wtk.behavior.CssStyle;
import org.wtk.component.list.ItemsContainer;

import java.util.Iterator;

/**
 * @author Yoav Aharoni
 */
public class PluginManager extends ItemsContainer<Plugin> implements IPluginManager {
	private static IPluginManagerLocator locator = new HierarchyPluginManagerLocator();

	public PluginManager(String id) {
		super(id);
		add(new CssStyle("display", "none"));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Iterator<Plugin> pluginsIterator() {
		return getItemsIterator();
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public <T extends Plugin> T getPlugin(Class<T> pluginClass) {
		final Iterator<Plugin> iterator = pluginsIterator();
		while (iterator.hasNext()) {
			final Plugin plugin = iterator.next();
			if (pluginClass.isAssignableFrom(plugin.getClass())) {
				return (T) plugin;
			}
		}
		return (T) createPlugin(pluginClass);
	}

	private Plugin createPlugin(Class<? extends Plugin> pluginClass) {
		try {
			Plugin plugin = pluginClass.newInstance();
			addItem(plugin);
			return plugin;
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public static IPluginManager get() {
		IPluginManager pluginManager = locator.locate();
		if (pluginManager == null) {
			throw new RuntimeException("PluginManager not found in page. Please make sure you've added PluginManager to your page or set a different Plugin Manager Locator (@see PluginManager.setLocator()).");
		}
		return pluginManager;
	}

	public static IPluginManager get(Page page) {
		IPluginManager pluginManager = locator.locate(page);
		if (pluginManager == null) {
			throw new RuntimeException("PluginManager not found in page. Please make sure you've added PluginManager to your page or set a different Plugin Manager Locator (@see PluginManager.setLocator()).");
		}
		return pluginManager;
	}

	public static IPluginManagerLocator getLocator() {
		return locator;
	}

	public static void setLocator(IPluginManagerLocator locator) {
		PluginManager.locator = locator;
	}
}
