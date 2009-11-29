package org.wtk.util;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;

/**
 * @author Yoav Aharoni
 */
public class ResourceUtils {
    public static IModel defaultResource(String key) {
        return new ResourceModel(key, "??" + key + "??");
    }
}
