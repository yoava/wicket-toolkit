package org.wtk.jquery.theme.vader;

import org.wtk.behavior.head.HeadResource;
import org.wtk.jquery.theme.uilightness.JQueryDefaultTheme;

/**
 * @author Yoav Aharoni
 */
public class JQueryVaderTheme extends HeadResource {
	private static final JQueryVaderTheme INSTANCE = new JQueryVaderTheme();

	private JQueryVaderTheme() {
		super(JQueryVaderTheme.class);
		replaces(JQueryDefaultTheme.get());
		addCss("global.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}
