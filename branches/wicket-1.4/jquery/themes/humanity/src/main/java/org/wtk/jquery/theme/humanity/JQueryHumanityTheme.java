package org.wtk.jquery.theme.humanity;

import org.wtk.behavior.head.HeadResource;
import org.wtk.jquery.theme.uilightness.JQueryDefaultTheme;

/**
 * @author Yoav Aharoni
 */
public class JQueryHumanityTheme extends HeadResource {
	private static final JQueryHumanityTheme INSTANCE = new JQueryHumanityTheme();

	private JQueryHumanityTheme() {
		super(JQueryHumanityTheme.class);
		replaces(JQueryDefaultTheme.get());
		addCss("global.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}
