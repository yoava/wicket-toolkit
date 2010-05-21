package org.wtk.jquery.resource.css;

import org.wtk.behavior.head.HeadResource;

/**
 * @author Yoav Aharoni
 */
public class JQueryUILightnessTheme extends HeadResource {
	private static final JQueryUILightnessTheme INSTANCE = new JQueryUILightnessTheme();

	private JQueryUILightnessTheme() {
		super(JQueryUILightnessTheme.class);
		replaces(JQueryDefaultTheme.get());
		addCss("ui-lightness/global.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}
