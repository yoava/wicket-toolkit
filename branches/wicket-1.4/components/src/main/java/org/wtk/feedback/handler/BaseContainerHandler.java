package org.wtk.feedback.handler;

import org.wtk.behavior.head.HeadResource;
import org.wtk.feedback.dispatcher.JsFeedbackDispatcherPlugin;

/**
 * @author Yoav Aharoni
 */
public class BaseContainerHandler extends HeadResource {
	private static final BaseContainerHandler INSTANCE = new BaseContainerHandler();

	private BaseContainerHandler() {
		super(BaseContainerHandler.class);
		dependsOn(JsFeedbackDispatcherPlugin.HEAD_RESOURCE);
		addJavaScript();
	}

	public static BaseContainerHandler get() {
		return INSTANCE;
	}
}
