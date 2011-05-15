package org.wtk.jquery.theme.trontastic;

import org.wtk.behavior.head.HeadResource;

/**
 * @author Yoav Aharoni
 */
public class JQueryTrontasticScopedTheme extends HeadResource {
	private static final JQueryTrontasticScopedTheme INSTANCE = new JQueryTrontasticScopedTheme();

	private JQueryTrontasticScopedTheme() {
		super(JQueryTrontasticScopedTheme.class);
		addCss("scoped.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}