package org.wtk.jquery.resource;

import org.wtk.behavior.head.HeadResource;
import org.wtk.jquery.resource.css.JQueryCommonCss;
import org.wtk.jquery.resource.css.JQueryDefaultTheme;
import org.wtk.resource.WicketToolkitHeadResource;


/**
 * @author Yoav Aharoni
 */
public class JQueryHeadResource extends HeadResource {
	private static final JQueryHeadResource INSTANCE = new JQueryHeadResource();

	private JQueryHeadResource() {
		super(JQueryHeadResource.class);
		dependsOn(JQueryCommonCss.get());
		dependsOn(JQueryDefaultTheme.get());
		replaces(WicketToolkitHeadResource.get());

		addJavaScript("js/jquery-1.3.2.min.js");
		addJavaScript();
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}
