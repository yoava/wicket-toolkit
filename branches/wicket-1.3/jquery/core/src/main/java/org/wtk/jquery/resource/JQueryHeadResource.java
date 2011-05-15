package org.wtk.jquery.resource;

import org.wtk.behavior.head.HeadResource;
import org.wtk.jquery.theme.common.JQueryCommonCss;
import org.wtk.jquery.theme.uilightness.JQueryDefaultTheme;
import org.wtk.resource.WicketToolkitHeadResource;


/**
 * @author Yoav Aharoni
 */
public class JQueryHeadResource extends HeadResource {
    private static final JQueryHeadResource INSTANCE = new JQueryHeadResource();

    private JQueryHeadResource() {
        super(JQueryHeadResource.class);
        dependsOn(WicketToolkitHeadResource.get());
        dependsOn(JQueryCommonCss.get());
        dependsOn(JQueryDefaultTheme.get());

        addJavaScript("jquery-1.5.min.js");
        addJavaScript();
    }

    public static HeadResource get() {
        return INSTANCE;
    }
}
