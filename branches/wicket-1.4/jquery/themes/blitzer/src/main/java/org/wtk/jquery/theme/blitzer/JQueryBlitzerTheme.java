package org.wtk.jquery.theme.blitzer;

import org.wtk.behavior.head.HeadResource;
import org.wtk.jquery.theme.uilightness.JQueryDefaultTheme;

/**
 * @author Yoav Aharoni
 */
public class JQueryBlitzerTheme extends HeadResource {
	private static final JQueryBlitzerTheme INSTANCE = new JQueryBlitzerTheme();

	private JQueryBlitzerTheme() {
		super(JQueryBlitzerTheme.class);
		replaces(JQueryDefaultTheme.get());
		addCss("global.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}
