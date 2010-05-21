package org.wtk.feedback.handler;

import org.wtk.feedback.dispatcher.BaseFeedbackHandler;
import org.wtk.util.JSBuilder;

/**
 * @author Yoav Aharoni
 */
public class AlertFeedbackHandler extends BaseFeedbackHandler {
	private static final AlertFeedbackHandler INSTANCE = new AlertFeedbackHandler();
	private static final String MESSAGE_JS_FORMAT = "${level} - ${message}";

	private String format;

	private AlertFeedbackHandler() {
		this(MESSAGE_JS_FORMAT);
	}

	private AlertFeedbackHandler(String format) {
		super(AlertFeedbackHandler.class, "AlertHandler");
		addJavaScript();
		this.format = new JSBuilder()
				.function("message", "level", "reporter")
				.append("return ")
				.interpolatedString(format)
				.end().toString();
	}

	@Override
	protected Object[] getParameters() {
		return new Object[]{format};
	}

	public static AlertFeedbackHandler get() {
		return INSTANCE;
	}

	public static AlertFeedbackHandler get(String format) {
		return new AlertFeedbackHandler(format);
	}
}
