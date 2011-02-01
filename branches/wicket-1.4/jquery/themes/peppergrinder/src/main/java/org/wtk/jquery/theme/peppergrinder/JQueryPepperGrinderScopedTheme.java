package org.wtk.jquery.theme.peppergrinder;

import org.wtk.behavior.head.HeadResource;

/**
 * @author Yoav Aharoni
 */
public class JQueryPepperGrinderScopedTheme extends HeadResource {
	private static final JQueryPepperGrinderScopedTheme INSTANCE = new JQueryPepperGrinderScopedTheme();

	private JQueryPepperGrinderScopedTheme() {
		super(JQueryPepperGrinderScopedTheme.class);
		addCss("scoped.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}