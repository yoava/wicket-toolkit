package org.wtk.jquery.theme.lefrog;

import org.wtk.behavior.head.HeadResource;
import org.wtk.jquery.theme.uilightness.JQueryDefaultTheme;

/**
 * @author Yoav Aharoni
 */
public class JQueryLeFrogTheme extends HeadResource {
	private static final JQueryLeFrogTheme INSTANCE = new JQueryLeFrogTheme();

	private JQueryLeFrogTheme() {
		super(JQueryLeFrogTheme.class);
		replaces(JQueryDefaultTheme.get());
		addCss("global.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}
