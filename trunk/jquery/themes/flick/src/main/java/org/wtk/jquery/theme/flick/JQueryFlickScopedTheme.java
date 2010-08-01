package org.wtk.jquery.theme.flick;

import org.wtk.behavior.head.HeadResource;

/**
 * @author Yoav Aharoni
 */
public class JQueryFlickScopedTheme extends HeadResource {
	private static final JQueryFlickScopedTheme INSTANCE = new JQueryFlickScopedTheme();

	private JQueryFlickScopedTheme() {
		super(JQueryFlickScopedTheme.class);
		addCss("scoped.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}