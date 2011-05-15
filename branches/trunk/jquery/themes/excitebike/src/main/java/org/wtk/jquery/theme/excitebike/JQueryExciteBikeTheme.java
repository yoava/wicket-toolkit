package org.wtk.jquery.theme.excitebike;

import org.wtk.behavior.head.HeadResource;
import org.wtk.jquery.theme.uilightness.JQueryDefaultTheme;

/**
 * @author Yoav Aharoni
 */
public class JQueryExciteBikeTheme extends HeadResource {
	private static final JQueryExciteBikeTheme INSTANCE = new JQueryExciteBikeTheme();

	private JQueryExciteBikeTheme() {
		super(JQueryExciteBikeTheme.class);
		replaces(JQueryDefaultTheme.get());
		addCss("global.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}
