package org.wtk.feedback;

import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebComponent;
import org.wtk.feedback.behavior.FeedbackTargetBehavior;
import org.wtk.feedback.handler.DefaultFeedbackBehavior;

/**
 * @author Yoav Aharoni
 */
public class FeedbackContainer extends WebComponent {
	public FeedbackContainer(String id) {
		super(id);
	}

	public FeedbackContainer addFeedbackSource(Component component) {
		component.add(new FeedbackTargetBehavior(this));
		return this;
	}

	@SuppressWarnings({"unchecked"})
	public FeedbackContainer setDefault(boolean defaultFeedback) {
		if (defaultFeedback) {
			add(new DefaultFeedbackBehavior());
		} else {
			for (DefaultFeedbackBehavior behavior : getBehaviors(DefaultFeedbackBehavior.class)) {
				remove(behavior);
			}
		}
		return this;
	}

	@Override
	protected void onComponentTagBody(MarkupStream markupStream, ComponentTag openTag) {
		replaceComponentTagBody(markupStream, openTag, "");
	}
}
