package org.wtk.jquery.theme.eggplant;

import org.wtk.behavior.head.HeadResource;

/**
 * @author Yoav Aharoni
 */
public class JQueryEggplantScopedTheme extends HeadResource {
	private static final JQueryEggplantScopedTheme INSTANCE = new JQueryEggplantScopedTheme();

	private JQueryEggplantScopedTheme() {
		super(JQueryEggplantScopedTheme.class);
		addCss("scoped.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}