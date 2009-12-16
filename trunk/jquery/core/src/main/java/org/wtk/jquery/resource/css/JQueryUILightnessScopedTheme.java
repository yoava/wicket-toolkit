package org.wtk.jquery.resource.css;

import org.wtk.behavior.head.HeadResource;

/**
 * @author Yoav Aharoni
 */
public class JQueryUILightnessScopedTheme extends HeadResource {
	private static final JQueryUILightnessScopedTheme INSTANCE = new JQueryUILightnessScopedTheme();

	private JQueryUILightnessScopedTheme() {
		super(JQueryUILightnessScopedTheme.class);
		addCss("ui-lightness/global.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}