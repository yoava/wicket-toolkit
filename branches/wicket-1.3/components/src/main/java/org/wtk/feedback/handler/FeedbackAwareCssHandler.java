package org.wtk.feedback.handler;

import org.wtk.feedback.dispatcher.BaseFeedbackHandler;

/**
 * @author Yoav Aharoni
 */
public class FeedbackAwareCssHandler extends BaseFeedbackHandler {
	private static final FeedbackAwareCssHandler INSTANCE = new FeedbackAwareCssHandler();
	private static final String DEFAULT_CSS_CLASS = "feedback-aware";

	private String cssClass;
	private boolean updateAllParents;

	private FeedbackAwareCssHandler() {
		this(DEFAULT_CSS_CLASS, false);
	}

	private FeedbackAwareCssHandler(String cssClass, boolean updateAllParents) {
		super(FeedbackAwareCssHandler.class, "FeedbackAwareCssHandler");
		this.cssClass = cssClass;
		this.updateAllParents = updateAllParents;
		addJavaScript();
	}

	@Override
	protected Object[] getParameters() {
		return new Object[]{cssClass, updateAllParents};
	}

	public static FeedbackAwareCssHandler get() {
		return INSTANCE;
	}

	public static FeedbackAwareCssHandler get(String cssClass, boolean updateAllParents) {
		return new FeedbackAwareCssHandler(cssClass, updateAllParents);
	}
}
