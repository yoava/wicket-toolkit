package org.wtk.feedback.handler;

import org.apache.wicket.Component;
import org.wtk.feedback.dispatcher.BaseFeedbackHandler;

/**
 * @author Yoav Aharoni
 */
public class DefaultFeedbackBehavior extends BaseFeedbackHandler {
	private Component component;

	public DefaultFeedbackBehavior() {
		super(DefaultFeedbackBehavior.class, "DefaultFeedbackHandler");
		dependsOn(BaseContainerHandler.get());
		addJavaScript();
	}

	@Override
	public void bind(Component component) {
		super.bind(component);
		component.setOutputMarkupPlaceholderTag(true);
		this.component = component;
	}

	@Override
	protected Object[] getParameters() {
		return new Object[]{component.getMarkupId()};
	}
}
