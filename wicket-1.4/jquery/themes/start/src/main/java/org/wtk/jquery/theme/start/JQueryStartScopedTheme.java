package org.wtk.jquery.theme.start;

import org.wtk.behavior.head.HeadResource;

/**
 * @author Yoav Aharoni
 */
public class JQueryStartScopedTheme extends HeadResource {
	private static final JQueryStartScopedTheme INSTANCE = new JQueryStartScopedTheme();

	private JQueryStartScopedTheme() {
		super(JQueryStartScopedTheme.class);
		addCss("scoped.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}