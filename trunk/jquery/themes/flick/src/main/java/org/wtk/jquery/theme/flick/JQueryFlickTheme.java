package org.wtk.jquery.theme.flick;

import org.wtk.behavior.head.HeadResource;
import org.wtk.jquery.theme.uilightness.JQueryDefaultTheme;

/**
 * @author Yoav Aharoni
 */
public class JQueryFlickTheme extends HeadResource {
	private static final JQueryFlickTheme INSTANCE = new JQueryFlickTheme();

	private JQueryFlickTheme() {
		super(JQueryFlickTheme.class);
		replaces(JQueryDefaultTheme.get());
		addCss("global.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}
