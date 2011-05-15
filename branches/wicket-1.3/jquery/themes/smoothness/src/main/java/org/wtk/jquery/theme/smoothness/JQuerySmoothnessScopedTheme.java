package org.wtk.jquery.theme.smoothness;

import org.wtk.behavior.head.HeadResource;

/**
 * @author Yoav Aharoni
 */
public class JQuerySmoothnessScopedTheme extends HeadResource {
	private static final JQuerySmoothnessScopedTheme INSTANCE = new JQuerySmoothnessScopedTheme();

	private JQuerySmoothnessScopedTheme() {
		super(JQuerySmoothnessScopedTheme.class);
		addCss("scoped.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}