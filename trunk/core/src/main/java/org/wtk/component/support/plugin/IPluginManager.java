package org.wtk.component.support.plugin;

import org.apache.wicket.Component;

import java.util.Collection;

/**
 * @author Yoav Aharoni
 */
public interface IPluginManager {
    <T extends Plugin> T getPlugin(Class<T> pluginClass);
    Collection<Plugin> listPlugins();
}
