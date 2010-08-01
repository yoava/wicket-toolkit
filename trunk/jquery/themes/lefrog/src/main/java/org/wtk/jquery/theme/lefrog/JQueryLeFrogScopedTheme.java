package org.wtk.jquery.theme.lefrog;

import org.wtk.behavior.head.HeadResource;

/**
 * @author Yoav Aharoni
 */
public class JQueryLeFrogScopedTheme extends HeadResource {
	private static final JQueryLeFrogScopedTheme INSTANCE = new JQueryLeFrogScopedTheme();

	private JQueryLeFrogScopedTheme() {
		super(JQueryLeFrogScopedTheme.class);
		addCss("scoped.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}