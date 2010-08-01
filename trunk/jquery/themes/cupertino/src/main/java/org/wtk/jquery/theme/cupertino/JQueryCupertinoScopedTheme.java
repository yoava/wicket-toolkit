package org.wtk.jquery.theme.cupertino;

import org.wtk.behavior.head.HeadResource;

/**
 * @author Yoav Aharoni
 */
public class JQueryCupertinoScopedTheme extends HeadResource {
	private static final JQueryCupertinoScopedTheme INSTANCE = new JQueryCupertinoScopedTheme();

	private JQueryCupertinoScopedTheme() {
		super(JQueryCupertinoScopedTheme.class);
		addCss("scoped.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}