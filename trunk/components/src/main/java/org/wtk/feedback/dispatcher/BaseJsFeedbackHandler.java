package org.wtk.feedback.dispatcher;

import org.apache.wicket.markup.html.IHeaderResponse;
import org.wtk.behavior.head.HeadResource;

/**
 * @author Yoav Aharoni
 */
public class BaseJsFeedbackHandler extends HeadResource {
	private String javascriptHandler;
	private String handlerId;

	public BaseJsFeedbackHandler(Class<?> scope, String javascriptHandler) {
		this(scope, javascriptHandler, javascriptHandler);
	}

	public BaseJsFeedbackHandler(Class<?> scope, String javascriptHandler, String handlerId) {
		super(scope);
		this.javascriptHandler = javascriptHandler;
		this.handlerId = handlerId;
		dependsOn(JsFeedbackDispatcherPlugin.HEAD_RESOURCE);
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.renderJavascript(getInitScript(), getScriptId());
	}

	protected String getScriptId() {
		return "feedbackHandler_" + javascriptHandler;
	}

	protected String getInitScript() {
		return String.format("wtk.feedback.addHandler(new wtk.feedback.%s('%s'))", javascriptHandler, handlerId);
	}
}
