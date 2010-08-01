package org.wtk.jquery.theme.start;

import org.wtk.behavior.head.HeadResource;
import org.wtk.jquery.theme.uilightness.JQueryDefaultTheme;

/**
 * @author Yoav Aharoni
 */
public class JQueryStartTheme extends HeadResource {
	private static final JQueryStartTheme INSTANCE = new JQueryStartTheme();

	private JQueryStartTheme() {
		super(JQueryStartTheme.class);
		replaces(JQueryDefaultTheme.get());
		addCss("global.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}
