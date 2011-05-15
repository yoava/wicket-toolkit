package org.wtk.jquery.theme.uilightness;

import org.wtk.behavior.head.HeadResource;

/**
 * @author Yoav Aharoni
 */
public class JQueryDefaultTheme extends HeadResource {
	private static final JQueryDefaultTheme INSTANCE = new JQueryDefaultTheme();

	private JQueryDefaultTheme() {
		super(JQueryDefaultTheme.class);
		addCss("global.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}