package org.wtk.jquery.theme.overcast;

import org.wtk.behavior.head.HeadResource;

/**
 * @author Yoav Aharoni
 */
public class JQueryOvercastScopedTheme extends HeadResource {
	private static final JQueryOvercastScopedTheme INSTANCE = new JQueryOvercastScopedTheme();

	private JQueryOvercastScopedTheme() {
		super(JQueryOvercastScopedTheme.class);
		addCss("scoped.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}