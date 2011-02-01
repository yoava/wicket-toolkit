package org.wtk.jquery.theme.peppergrinder;

import org.wtk.behavior.head.HeadResource;
import org.wtk.jquery.theme.uilightness.JQueryDefaultTheme;

/**
 * @author Yoav Aharoni
 */
public class JQueryPepperGrinderTheme extends HeadResource {
	private static final JQueryPepperGrinderTheme INSTANCE = new JQueryPepperGrinderTheme();

	private JQueryPepperGrinderTheme() {
		super(JQueryPepperGrinderTheme.class);
		replaces(JQueryDefaultTheme.get());
		addCss("global.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}
