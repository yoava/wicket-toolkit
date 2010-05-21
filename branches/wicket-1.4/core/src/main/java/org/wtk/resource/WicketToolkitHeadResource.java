package org.wtk.resource;

import org.apache.wicket.ajax.WicketAjaxReference;
import org.apache.wicket.markup.html.JavascriptPackageResource;
import org.apache.wicket.markup.html.WicketEventReference;
import org.wtk.behavior.head.HeadResource;

/**
 * @author Yoav Aharoni
 */
public final class WicketToolkitHeadResource extends HeadResource {
	private static final WicketToolkitHeadResource INSTANCE = new WicketToolkitHeadResource();

	private WicketToolkitHeadResource() {
		super(WicketToolkitHeadResource.class);
		dependsOn(JavascriptPackageResource.getHeaderContribution(WicketAjaxReference.INSTANCE));
		dependsOn(JavascriptPackageResource.getHeaderContribution(WicketEventReference.INSTANCE));
		addJavaScript();
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}
