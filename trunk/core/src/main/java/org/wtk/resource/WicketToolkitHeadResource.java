package org.wtk.resource;

import org.wtk.behavior.head.HeadResource;

/**
 * @author Yoav Aharoni
 */
public final class WicketToolkitHeadResource extends HeadResource {
	private static final WicketToolkitHeadResource INSTANCE = new WicketToolkitHeadResource();

	private WicketToolkitHeadResource() {
		super(WicketToolkitHeadResource.class);
		addJavaScript();
	}

	public static HeadResource get() {
		return INSTANCE;
	}
}
