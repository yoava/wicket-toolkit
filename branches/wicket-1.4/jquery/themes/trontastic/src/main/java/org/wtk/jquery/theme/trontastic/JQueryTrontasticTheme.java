package org.wtk.jquery.theme.trontastic;

import org.wtk.behavior.head.HeadResource;
import org.wtk.jquery.theme.uilightness.JQueryDefaultTheme;

/**
 * @author Yoav Aharoni
 */
public class JQueryTrontasticTheme extends HeadResource {
	private static final JQueryTrontasticTheme INSTANCE = new JQueryTrontasticTheme();

	private JQueryTrontasticTheme() {
		super(JQueryTrontasticTheme.class);
		replaces(JQueryDefaultTheme.get());
		addCss("global.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}
