package org.wtk.jquery.theme.uilightness;

import org.wtk.behavior.head.HeadResource;

/**
 * @author Yoav Aharoni
 */
public class JQueryUILightnessScopedTheme extends HeadResource {
	private static final JQueryUILightnessScopedTheme INSTANCE = new JQueryUILightnessScopedTheme();

	private JQueryUILightnessScopedTheme() {
		super(JQueryUILightnessScopedTheme.class);
		addCss("scoped.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}