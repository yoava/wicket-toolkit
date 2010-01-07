package org.wtk.feedback.handler;

import org.wtk.behavior.head.HeadResource;

/**
 * @author Yoav Aharoni
 */
public class BaseContainerHandler extends HeadResource {
	private static final BaseContainerHandler INSTANCE = new BaseContainerHandler();

	private BaseContainerHandler() {
		super(BaseContainerHandler.class);
		addJavaScript();
	}

	public static BaseContainerHandler get() {
		return INSTANCE;
	}
}
