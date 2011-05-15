package org.wtk.jquery.theme.redmond;

import org.wtk.behavior.head.HeadResource;

/**
 * @author Yoav Aharoni
 */
public class JQueryRedmondScopedTheme extends HeadResource {
	private static final JQueryRedmondScopedTheme INSTANCE = new JQueryRedmondScopedTheme();

	private JQueryRedmondScopedTheme() {
		super(JQueryRedmondScopedTheme.class);
		addCss("scoped.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}