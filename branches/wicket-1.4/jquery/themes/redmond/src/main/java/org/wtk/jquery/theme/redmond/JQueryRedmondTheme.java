package org.wtk.jquery.theme.redmond;

import org.wtk.behavior.head.HeadResource;
import org.wtk.jquery.theme.uilightness.JQueryDefaultTheme;

/**
 * @author Yoav Aharoni
 */
public class JQueryRedmondTheme extends HeadResource {
	private static final JQueryRedmondTheme INSTANCE = new JQueryRedmondTheme();

	private JQueryRedmondTheme() {
		super(JQueryRedmondTheme.class);
		replaces(JQueryDefaultTheme.get());
		addCss("global.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}
