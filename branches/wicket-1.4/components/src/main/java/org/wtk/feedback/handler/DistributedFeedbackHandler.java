package org.wtk.feedback.handler;

import org.wtk.feedback.dispatcher.BaseFeedbackHandler;

/**
 * @author Yoav Aharoni
 */
public class DistributedFeedbackHandler extends BaseFeedbackHandler {
	private static final DistributedFeedbackHandler INSTANCE = new DistributedFeedbackHandler();

	private DistributedFeedbackHandler() {
		super(DistributedFeedbackHandler.class, "DistributeHandler");
		dependsOn(BaseContainerHandler.get());
		addJavaScript();
	}

	public static DistributedFeedbackHandler get() {
		return INSTANCE;
	}
}
