package org.wtk.jquery.resource;

import org.wtk.behavior.HeadResource;
import org.wtk.jquery.model.JQuery;

/**
 * @author Yoav Aharoni
 */
public class JQuerySkin extends HeadResource {
	public JQuerySkin(JQuery.Theme theme) {
		this(theme, false);
	}

	public JQuerySkin(JQuery.Theme theme, boolean scoped) {
		final String fileName = scoped ? "scoped" : "global";
		addCss(String.format("css/%s/%s.css", theme.getTheme(), fileName));
	}
}
