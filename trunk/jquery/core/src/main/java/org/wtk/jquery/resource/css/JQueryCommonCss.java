package org.wtk.jquery.resource.css;

import org.wtk.behavior.head.HeadResource;

/**
 * @author Yoav Aharoni
 */
public class JQueryCommonCss extends HeadResource {
	private static final JQueryCommonCss INSTANCE = new JQueryCommonCss();

	private JQueryCommonCss() {
		super(JQueryCommonCss.class);
		addCss("common.css").setInline(true);
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}

