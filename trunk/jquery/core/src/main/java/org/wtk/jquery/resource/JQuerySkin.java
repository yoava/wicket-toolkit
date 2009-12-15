package org.wtk.jquery.resource;

import org.wtk.behavior.head.HeadResource;
import org.wtk.jquery.model.JQuery;

/**
 * @author Yoav Aharoni
 */
public class JQuerySkin extends HeadResource {
	public JQuerySkin(JQuery.Theme theme) {
		this(theme, false);
	}

	public JQuerySkin(JQuery.Theme theme, boolean scoped) {
		super(JQuerySkin.class);
		final String fileName = scoped ? "scoped" : "global";
		addCss("css/common.css");
		addCss(String.format("css/%s/%s.css", theme.getValue(), fileName));
	}
}
