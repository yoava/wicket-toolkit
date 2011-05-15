package org.wtk.jquery.theme.sunny;

import org.wtk.behavior.head.HeadResource;
import org.wtk.jquery.theme.uilightness.JQueryDefaultTheme;

/**
 * @author Yoav Aharoni
 */
public class JQuerySunnyTheme extends HeadResource {
	private static final JQuerySunnyTheme INSTANCE = new JQuerySunnyTheme();

	private JQuerySunnyTheme() {
		super(JQuerySunnyTheme.class);
		replaces(JQueryDefaultTheme.get());
		addCss("global.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}
