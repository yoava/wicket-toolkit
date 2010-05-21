package org.wtk.component.support.plugin;

import java.util.Iterator;

/**
 * @author Yoav Aharoni
 */
public interface IPluginManager {
	<T extends Plugin> T getPlugin(Class<T> pluginClass);

	Iterator<Plugin> pluginsIterator();
}
