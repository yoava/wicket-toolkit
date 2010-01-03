package org.wtk.feedback.handler;

import org.wtk.feedback.dispatcher.BaseJsFeedbackHandler;

/**
 * @author Yoav Aharoni
 */
public class AlertFeedbackHandler extends BaseJsFeedbackHandler {
	private static final AlertFeedbackHandler INSTANCE = new AlertFeedbackHandler();

	private String format;

	private AlertFeedbackHandler() {
		this("level + ' - ' + message");
	}

	private AlertFeedbackHandler(String format) {
		super(AlertFeedbackHandler.class, "AlertHandler");
		addJavaScript().setTemplateModel(this);
		this.format = format;
	}

	public String getFormat() {
		return format;
	}

	public static AlertFeedbackHandler get() {
		return INSTANCE;
	}

	public static AlertFeedbackHandler get(String format) {
		return new AlertFeedbackHandler(format);
	}
}
