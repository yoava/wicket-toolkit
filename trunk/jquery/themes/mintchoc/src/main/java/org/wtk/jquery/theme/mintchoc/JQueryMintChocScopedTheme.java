package org.wtk.jquery.theme.mintchoc;

import org.wtk.behavior.head.HeadResource;

/**
 * @author Yoav Aharoni
 */
public class JQueryMintChocScopedTheme extends HeadResource {
	private static final JQueryMintChocScopedTheme INSTANCE = new JQueryMintChocScopedTheme();

	private JQueryMintChocScopedTheme() {
		super(JQueryMintChocScopedTheme.class);
		addCss("scoped.css");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}