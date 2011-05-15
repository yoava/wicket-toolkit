package org.wtk.jquery.behavior.feedback;

import org.apache.commons.lang.StringUtils;
import org.wtk.feedback.dispatcher.BaseFeedbackHandler;
import org.wtk.jquery.resource.JQueryHeadResource;

/**
 * @author Yoav Aharoni
 */
public class FieldMarkerFeedbackHandler extends BaseFeedbackHandler {
	private static final FieldMarkerFeedbackHandler INSTANCE = new FieldMarkerFeedbackHandler();
	private static final String DEFAULT_CSS_CLASS = "feedback-marker";

	private String cssClass;

	private FieldMarkerFeedbackHandler() {
		this(DEFAULT_CSS_CLASS);
	}

	private FieldMarkerFeedbackHandler(String cssClass) {
		super(FieldMarkerFeedbackHandler.class, "FieldMarker");
		this.cssClass = cssClass;

		dependsOn(JQueryHeadResource.get());
		addJavaScript();
	}

	@Override
	protected Object[] getParameters() {
		return new String[]{cssClass};
	}

	public static FieldMarkerFeedbackHandler get() {
		return INSTANCE;
	}

	public static FieldMarkerFeedbackHandler get(String cssClass) {
		if (StringUtils.isEmpty(cssClass) || cssClass.equals(DEFAULT_CSS_CLASS)) {
			return get();
		}

		return new FieldMarkerFeedbackHandler(cssClass);
	}
}
