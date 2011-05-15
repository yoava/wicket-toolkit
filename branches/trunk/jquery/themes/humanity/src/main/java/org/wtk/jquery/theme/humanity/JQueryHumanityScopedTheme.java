package org.wtk.jquery.theme.humanity;

import org.wtk.behavior.head.HeadResource;

/**
 * @author Yoav Aharoni
 */
public class JQueryHumanityScopedTheme extends HeadResource {
	private static final JQueryHumanityScopedTheme INSTANCE = new JQueryHumanityScopedTheme();

	private JQueryHumanityScopedTheme() {
		super(JQueryHumanityScopedTheme.class);
		addCss("scoped.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}