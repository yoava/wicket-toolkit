package org.wtk.jquery.theme.overcast;

import org.wtk.behavior.head.HeadResource;
import org.wtk.jquery.theme.uilightness.JQueryDefaultTheme;

/**
 * @author Yoav Aharoni
 */
public class JQueryOvercastTheme extends HeadResource {
	private static final JQueryOvercastTheme INSTANCE = new JQueryOvercastTheme();

	private JQueryOvercastTheme() {
		super(JQueryOvercastTheme.class);
		replaces(JQueryDefaultTheme.get());
		addCss("global.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}
