package org.wtk.jquery.resource;

import org.wtk.behavior.head.HeadResource;


/**
 * @author Yoav Aharoni
 */
public class JQueryUIHeadResource extends HeadResource {
	private static final JQueryUIHeadResource INSTANCE = new JQueryUIHeadResource();

	private JQueryUIHeadResource() {
		super(JQueryUIHeadResource.class);
		dependsOn(JQueryHeadResource.get());
		addJavaScript("js/jquery-ui-1.8.2.custom.min.js");
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}