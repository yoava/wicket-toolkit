package org.wtk.jquery.theme.blacktie;

import org.wtk.behavior.head.HeadResource;

/**
 * @author Yoav Aharoni
 */
public class JQueryBlackTieScopedTheme extends HeadResource {
	private static final JQueryBlackTieScopedTheme INSTANCE = new JQueryBlackTieScopedTheme();

	private JQueryBlackTieScopedTheme() {
		super(JQueryBlackTieScopedTheme.class);
		addCss("scoped.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}