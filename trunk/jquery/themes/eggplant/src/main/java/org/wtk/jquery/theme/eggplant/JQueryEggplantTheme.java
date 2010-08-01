package org.wtk.jquery.theme.eggplant;

import org.wtk.behavior.head.HeadResource;
import org.wtk.jquery.theme.uilightness.JQueryDefaultTheme;

/**
 * @author Yoav Aharoni
 */
public class JQueryEggplantTheme extends HeadResource {
	private static final JQueryEggplantTheme INSTANCE = new JQueryEggplantTheme();

	private JQueryEggplantTheme() {
		super(JQueryEggplantTheme.class);
		replaces(JQueryDefaultTheme.get());
		addCss("global.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}
