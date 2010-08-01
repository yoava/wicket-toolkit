package org.wtk.jquery.theme.sunny;

import org.wtk.behavior.head.HeadResource;

/**
 * @author Yoav Aharoni
 */
public class JQuerySunnyScopedTheme extends HeadResource {
	private static final JQuerySunnyScopedTheme INSTANCE = new JQuerySunnyScopedTheme();

	private JQuerySunnyScopedTheme() {
		super(JQuerySunnyScopedTheme.class);
		addCss("scoped.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}