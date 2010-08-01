package org.wtk.jquery.theme.southstreet;

import org.wtk.behavior.head.HeadResource;

/**
 * @author Yoav Aharoni
 */
public class JQuerySouthStreetScopedTheme extends HeadResource {
	private static final JQuerySouthStreetScopedTheme INSTANCE = new JQuerySouthStreetScopedTheme();

	private JQuerySouthStreetScopedTheme() {
		super(JQuerySouthStreetScopedTheme.class);
		addCss("scoped.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}