package org.wtk.jquery.resource;

import org.wtk.behavior.head.HeadResource;


/**
 * @author Yoav Aharoni
 */
public class JQueryUIHeadResource extends HeadResource {
	public JQueryUIHeadResource() {
		super(JQueryUIHeadResource.class);
		dependsOn(new JQueryHeadResource());
		addJavaScript("js/jquery-ui-1.7.2.custom.min.js");
	}
}