package org.wtk.jquery.resource;

import org.wtk.behavior.head.HeadResource;
import org.wtk.resource.WicketToolkitHeadResource;


/**
 * @author Yoav Aharoni
 */
public class JQueryHeadResource extends HeadResource {
	public JQueryHeadResource() {
		super(JQueryHeadResource.class);
		addJavaScript("js/jquery-1.3.2.min.js");
		addJavaScript();
		replaces(new WicketToolkitHeadResource());
	}
}
