package org.wtk.component.support.plugin;

import org.wtk.component.list.CollectionPanel;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Yoav Aharoni
 */
public class PluginManager extends CollectionPanel<Plugin> implements IPluginManager {
    private static IPluginManagerLocator locator = new HierarchyPluginManagerLocator();

    public PluginManager(String id) {
        super(id, new ArrayList<Plugin>());
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public Collection<Plugin> listPlugins() {
        return getItems();
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public <T extends Plugin> T getPlugin(Class<T> pluginClass) {
        for (Plugin plugin : listPlugins()) {
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
            throw new RuntimeException("PluginManager not found in page. Please make sure you've added PluginManager to your page or set a different Plugin Manager Locator.");
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
