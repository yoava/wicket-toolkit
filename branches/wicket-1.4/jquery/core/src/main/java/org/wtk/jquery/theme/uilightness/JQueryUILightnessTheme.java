package org.wtk.jquery.theme.uilightness;

import org.wtk.behavior.head.HeadResource;

/**
 * @author Yoav Aharoni
 */
public class JQueryUILightnessTheme extends HeadResource {
	private static final JQueryUILightnessTheme INSTANCE = new JQueryUILightnessTheme();

	private JQueryUILightnessTheme() {
		super(JQueryUILightnessTheme.class);
		replaces(JQueryDefaultTheme.get());
		addCss("global.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}
