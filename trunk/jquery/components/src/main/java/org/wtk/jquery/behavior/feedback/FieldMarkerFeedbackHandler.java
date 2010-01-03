package org.wtk.jquery.behavior.feedback;

import org.wtk.feedback.dispatcher.BaseJsFeedbackHandler;
import org.wtk.jquery.resource.JQueryHeadResource;

/**
 * @author Yoav Aharoni
 */
public class FieldMarkerFeedbackHandler extends BaseJsFeedbackHandler {
	private static final FieldMarkerFeedbackHandler INSTANCE = new FieldMarkerFeedbackHandler();

	private FieldMarkerFeedbackHandler() {
		super(FieldMarkerFeedbackHandler.class, "FieldMarker");
		dependsOn(JQueryHeadResource.get());
		addJavaScript();
	}

	public static FieldMarkerFeedbackHandler get() {
		return INSTANCE;
	}
}
