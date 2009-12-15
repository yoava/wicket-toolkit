package org.wtk.resource;

import org.wtk.behavior.head.HeadResource;

/**
 * @author Yoav Aharoni
 */
public final class WicketToolkitHeadResource extends HeadResource {
	public WicketToolkitHeadResource() {
		super(WicketToolkitHeadResource.class);
		addJavaScript();
	}
}
