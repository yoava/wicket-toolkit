package org.wtk.feedback.handler;

import org.wtk.feedback.dispatcher.BaseJsFeedbackHandler;

/**
 * @author Yoav Aharoni
 */
public class DistributedFeedbackHandler extends BaseJsFeedbackHandler {
	private static final DistributedFeedbackHandler INSTANCE = new DistributedFeedbackHandler();

	private DistributedFeedbackHandler() {
		super(DistributedFeedbackHandler.class, "DistributeHandler");
		addJavaScript();
	}

	public static DistributedFeedbackHandler get() {
		return INSTANCE;
	}
}
