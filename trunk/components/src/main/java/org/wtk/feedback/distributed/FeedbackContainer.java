package org.wtk.feedback.distributed;

import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebComponent;

/**
 * @author Yoav Aharoni
 */
public class FeedbackContainer extends WebComponent {
	public FeedbackContainer(String id) {
		super(id);
	}

	@Override
	protected void onComponentTagBody(MarkupStream markupStream, ComponentTag openTag) {
		replaceComponentTagBody(markupStream, openTag, "");
	}

	public FeedbackContainer addFeedbackSource(Component component) {
		component.add(new FeedbackTargetBehavior(this));
		return this;
	}
}
