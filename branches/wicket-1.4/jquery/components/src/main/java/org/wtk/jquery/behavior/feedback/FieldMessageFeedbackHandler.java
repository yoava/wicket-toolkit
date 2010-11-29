package org.wtk.jquery.behavior.feedback;

import org.wtk.feedback.dispatcher.BaseFeedbackHandler;
import org.wtk.feedback.handler.BaseContainerHandler;

/**
 * @author Yoav Aharoni
 */
public class FieldMessageFeedbackHandler extends BaseFeedbackHandler {
	private static final FieldMessageFeedbackHandler INSTANCE = new FieldMessageFeedbackHandler();
	private static final int DEFAULT_PRIORITY = 100;

	private int priority;

	private FieldMessageFeedbackHandler() {
		this(DEFAULT_PRIORITY);
	}

	private FieldMessageFeedbackHandler(int priority) {
		super(FieldMessageFeedbackHandler.class, "FieldMessage");
		this.priority = priority;

		dependsOn(BaseContainerHandler.get());
		addJavaScript();
	}

	@Override
	protected Object[] getParameters() {
		return new Object[]{priority};
	}

	public static FieldMessageFeedbackHandler get() {
		return INSTANCE;
	}

	public static FieldMessageFeedbackHandler get(int priority) {
		if (priority == DEFAULT_PRIORITY) {
			return get();
		}

		return new FieldMessageFeedbackHandler(priority);
	}
}
