package org.wtk.jquery.theme.southstreet;

import org.wtk.behavior.head.HeadResource;
import org.wtk.jquery.theme.uilightness.JQueryDefaultTheme;

/**
 * @author Yoav Aharoni
 */
public class JQuerySouthStreetTheme extends HeadResource {
	private static final JQuerySouthStreetTheme INSTANCE = new JQuerySouthStreetTheme();

	private JQuerySouthStreetTheme() {
		super(JQuerySouthStreetTheme.class);
		replaces(JQueryDefaultTheme.get());
		addCss("global.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}
