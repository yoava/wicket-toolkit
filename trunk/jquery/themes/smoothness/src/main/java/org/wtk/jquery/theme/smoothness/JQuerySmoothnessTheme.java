package org.wtk.jquery.theme.smoothness;

import org.wtk.behavior.head.HeadResource;
import org.wtk.jquery.theme.uilightness.JQueryDefaultTheme;

/**
 * @author Yoav Aharoni
 */
public class JQuerySmoothnessTheme extends HeadResource {
	private static final JQuerySmoothnessTheme INSTANCE = new JQuerySmoothnessTheme();

	private JQuerySmoothnessTheme() {
		super(JQuerySmoothnessTheme.class);
		replaces(JQueryDefaultTheme.get());
		addCss("global.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}
