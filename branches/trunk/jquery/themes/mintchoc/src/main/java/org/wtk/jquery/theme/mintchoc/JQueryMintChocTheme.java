package org.wtk.jquery.theme.mintchoc;

import org.wtk.behavior.head.HeadResource;
import org.wtk.jquery.theme.uilightness.JQueryDefaultTheme;

/**
 * @author Yoav Aharoni
 */
public class JQueryMintChocTheme extends HeadResource {
	private static final JQueryMintChocTheme INSTANCE = new JQueryMintChocTheme();

	private JQueryMintChocTheme() {
		super(JQueryMintChocTheme.class);
		replaces(JQueryDefaultTheme.get());
		addCss("global.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}
