package org.wtk.jquery.theme.cupertino;

import org.wtk.behavior.head.HeadResource;
import org.wtk.jquery.theme.uilightness.JQueryDefaultTheme;

/**
 * @author Yoav Aharoni
 */
public class JQueryCupertinoTheme extends HeadResource {
	private static final JQueryCupertinoTheme INSTANCE = new JQueryCupertinoTheme();

	private JQueryCupertinoTheme() {
		super(JQueryCupertinoTheme.class);
		replaces(JQueryDefaultTheme.get());
		addCss("global.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}
