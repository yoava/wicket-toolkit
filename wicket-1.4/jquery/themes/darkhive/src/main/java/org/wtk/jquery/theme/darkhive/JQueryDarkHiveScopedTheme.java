package org.wtk.jquery.theme.darkhive;

import org.wtk.behavior.head.HeadResource;

/**
 * @author Yoav Aharoni
 */
public class JQueryDarkHiveScopedTheme extends HeadResource {
	private static final JQueryDarkHiveScopedTheme INSTANCE = new JQueryDarkHiveScopedTheme();

	private JQueryDarkHiveScopedTheme() {
		super(JQueryDarkHiveScopedTheme.class);
		addCss("scoped.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}