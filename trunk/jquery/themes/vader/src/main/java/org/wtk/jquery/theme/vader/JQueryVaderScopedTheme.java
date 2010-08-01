package org.wtk.jquery.theme.vader;

import org.wtk.behavior.head.HeadResource;

/**
 * @author Yoav Aharoni
 */
public class JQueryVaderScopedTheme extends HeadResource {
	private static final JQueryVaderScopedTheme INSTANCE = new JQueryVaderScopedTheme();

	private JQueryVaderScopedTheme() {
		super(JQueryVaderScopedTheme.class);
		addCss("scoped.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}