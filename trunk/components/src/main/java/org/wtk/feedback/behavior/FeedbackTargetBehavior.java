package org.wtk.feedback.behavior;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.SimpleAttributeModifier;

/**
 * @author Yoav Aharoni
 */
public class FeedbackTargetBehavior extends SimpleAttributeModifier {
	public FeedbackTargetBehavior(Component targetFeedback) {
		this(targetFeedback.getMarkupId());
		targetFeedback.setOutputMarkupPlaceholderTag(true);
	}

	public FeedbackTargetBehavior(String feedbackMarkupId) {
		super("wtk:feedback", feedbackMarkupId);
	}
}
