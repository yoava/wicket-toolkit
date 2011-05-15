package org.wtk.jquery.theme.swankypurse;

import org.wtk.behavior.head.HeadResource;
import org.wtk.jquery.theme.uilightness.JQueryDefaultTheme;

/**
 * @author Yoav Aharoni
 */
public class JQuerySwankyPurseTheme extends HeadResource {
	private static final JQuerySwankyPurseTheme INSTANCE = new JQuerySwankyPurseTheme();

	private JQuerySwankyPurseTheme() {
		super(JQuerySwankyPurseTheme.class);
		replaces(JQueryDefaultTheme.get());
		addCss("global.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}
