package org.wtk.jquery.theme.common;

import org.wtk.behavior.head.HeadResource;

/**
 * @author Yoav Aharoni
 */
public class JQueryCommonCss extends HeadResource {
	private static final JQueryCommonCss INSTANCE = new JQueryCommonCss();

	private JQueryCommonCss() {
		super(JQueryCommonCss.class);
		addCss("common.css").setInline(true).setCached(true);
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}

