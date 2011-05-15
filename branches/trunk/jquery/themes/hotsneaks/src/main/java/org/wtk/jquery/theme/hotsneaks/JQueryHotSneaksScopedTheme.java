package org.wtk.jquery.theme.hotsneaks;

import org.wtk.behavior.head.HeadResource;

/**
 * @author Yoav Aharoni
 */
public class JQueryHotSneaksScopedTheme extends HeadResource {
	private static final JQueryHotSneaksScopedTheme INSTANCE = new JQueryHotSneaksScopedTheme();

	private JQueryHotSneaksScopedTheme() {
		super(JQueryHotSneaksScopedTheme.class);
		addCss("scoped.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}