package org.wtk.jquery.theme.swankypurse;

import org.wtk.behavior.head.HeadResource;

/**
 * @author Yoav Aharoni
 */
public class JQuerySwankyPurseScopedTheme extends HeadResource {
	private static final JQuerySwankyPurseScopedTheme INSTANCE = new JQuerySwankyPurseScopedTheme();

	private JQuerySwankyPurseScopedTheme() {
		super(JQuerySwankyPurseScopedTheme.class);
		addCss("scoped.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}