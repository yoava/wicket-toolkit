package org.wtk.feedback.handler;

import org.wtk.feedback.dispatcher.BaseFeedbackHandler;

/**
 * @author Yoav Aharoni
 */
public class ToogleCssClassFeedbackHandler extends BaseFeedbackHandler {
	private static final ToogleCssClassFeedbackHandler INSTANCE = new ToogleCssClassFeedbackHandler();

	private ToogleCssClassFeedbackHandler() {
		super(ToogleCssClassFeedbackHandler.class, "ToogleCssHandler");
		addJavaScript();
	}

	public static ToogleCssClassFeedbackHandler get() {
		return INSTANCE;
	}
}
