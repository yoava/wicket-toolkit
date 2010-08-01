package org.wtk.jquery.theme.blitzer;

import org.wtk.behavior.head.HeadResource;

/**
 * @author Yoav Aharoni
 */
public class JQueryBlitzerScopedTheme extends HeadResource {
	private static final JQueryBlitzerScopedTheme INSTANCE = new JQueryBlitzerScopedTheme();

	private JQueryBlitzerScopedTheme() {
		super(JQueryBlitzerScopedTheme.class);
		addCss("scoped.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}