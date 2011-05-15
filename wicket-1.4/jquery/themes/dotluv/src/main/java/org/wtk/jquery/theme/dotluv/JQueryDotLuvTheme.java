package org.wtk.jquery.theme.dotluv;

import org.wtk.behavior.head.HeadResource;
import org.wtk.jquery.theme.uilightness.JQueryDefaultTheme;

/**
 * @author Yoav Aharoni
 */
public class JQueryDotLuvTheme extends HeadResource {
	private static final JQueryDotLuvTheme INSTANCE = new JQueryDotLuvTheme();

	private JQueryDotLuvTheme() {
		super(JQueryDotLuvTheme.class);
		replaces(JQueryDefaultTheme.get());
		addCss("global.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}
