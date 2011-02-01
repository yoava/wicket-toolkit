package org.wtk.jquery.theme.uidarkness;

import org.wtk.behavior.head.HeadResource;
import org.wtk.jquery.theme.uilightness.JQueryDefaultTheme;

/**
 * @author Yoav Aharoni
 */
public class JQueryUiDarknessTheme extends HeadResource {
	private static final JQueryUiDarknessTheme INSTANCE = new JQueryUiDarknessTheme();

	private JQueryUiDarknessTheme() {
		super(JQueryUiDarknessTheme.class);
		replaces(JQueryDefaultTheme.get());
		addCss("global.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}
