package org.wtk.jquery.theme.hotsneaks;

import org.wtk.behavior.head.HeadResource;
import org.wtk.jquery.theme.uilightness.JQueryDefaultTheme;

/**
 * @author Yoav Aharoni
 */
public class JQueryHotSneaksTheme extends HeadResource {
	private static final JQueryHotSneaksTheme INSTANCE = new JQueryHotSneaksTheme();

	private JQueryHotSneaksTheme() {
		super(JQueryHotSneaksTheme.class);
		replaces(JQueryDefaultTheme.get());
		addCss("global.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}
