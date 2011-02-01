package org.wtk.jquery.theme.uidarkness;

import org.wtk.behavior.head.HeadResource;

/**
 * @author Yoav Aharoni
 */
public class JQueryUiDarknessScopedTheme extends HeadResource {
	private static final JQueryUiDarknessScopedTheme INSTANCE = new JQueryUiDarknessScopedTheme();

	private JQueryUiDarknessScopedTheme() {
		super(JQueryUiDarknessScopedTheme.class);
		addCss("scoped.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}