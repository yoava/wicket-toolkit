package org.wtk.jquery.theme.excitebike;

import org.wtk.behavior.head.HeadResource;

/**
 * @author Yoav Aharoni
 */
public class JQueryExciteBikeScopedTheme extends HeadResource {
	private static final JQueryExciteBikeScopedTheme INSTANCE = new JQueryExciteBikeScopedTheme();

	private JQueryExciteBikeScopedTheme() {
		super(JQueryExciteBikeScopedTheme.class);
		addCss("scoped.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}