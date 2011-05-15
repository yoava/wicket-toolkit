package org.wtk.jquery.theme.darkhive;

import org.wtk.behavior.head.HeadResource;
import org.wtk.jquery.theme.uilightness.JQueryDefaultTheme;

/**
 * @author Yoav Aharoni
 */
public class JQueryDarkHiveTheme extends HeadResource {
	private static final JQueryDarkHiveTheme INSTANCE = new JQueryDarkHiveTheme();

	private JQueryDarkHiveTheme() {
		super(JQueryDarkHiveTheme.class);
		replaces(JQueryDefaultTheme.get());
		addCss("global.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}
