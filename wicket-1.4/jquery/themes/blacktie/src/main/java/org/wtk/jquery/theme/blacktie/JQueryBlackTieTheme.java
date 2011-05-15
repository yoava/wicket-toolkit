package org.wtk.jquery.theme.blacktie;

import org.wtk.behavior.head.HeadResource;
import org.wtk.jquery.theme.uilightness.JQueryDefaultTheme;

/**
 * @author Yoav Aharoni
 */
public class JQueryBlackTieTheme extends HeadResource {
	private static final JQueryBlackTieTheme INSTANCE = new JQueryBlackTieTheme();

	private JQueryBlackTieTheme() {
		super(JQueryBlackTieTheme.class);
		replaces(JQueryDefaultTheme.get());
		addCss("global.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}
