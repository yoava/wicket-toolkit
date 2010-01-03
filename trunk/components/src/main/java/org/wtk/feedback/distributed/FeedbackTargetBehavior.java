package org.wtk.feedback.distributed;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.wtk.feedback.handler.DistributedFeedbackHandler;

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

	@Override
	public void bind(Component component) {
		super.bind(component);
		component.add(DistributedFeedbackHandler.get());
	}
}
