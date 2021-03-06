package org.wtk.jquery.theme.dotluv;

import org.wtk.behavior.head.HeadResource;

/**
 * @author Yoav Aharoni
 */
public class JQueryDotLuvScopedTheme extends HeadResource {
	private static final JQueryDotLuvScopedTheme INSTANCE = new JQueryDotLuvScopedTheme();

	private JQueryDotLuvScopedTheme() {
		super(JQueryDotLuvScopedTheme.class);
		addCss("scoped.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}