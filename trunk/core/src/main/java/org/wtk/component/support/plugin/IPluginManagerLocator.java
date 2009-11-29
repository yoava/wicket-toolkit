package org.wtk.component.support.plugin;

import org.apache.wicket.Page;

/**
 * @author Yoav Aharoni
 */
public interface IPluginManagerLocator {
    IPluginManager locate();

    IPluginManager locate(Page page);
}
