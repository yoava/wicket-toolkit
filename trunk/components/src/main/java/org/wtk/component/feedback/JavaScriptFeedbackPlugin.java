package org.wtk.component.feedback;

import org.apache.wicket.Component;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.FeedbackMessagesModel;
import org.wtk.component.support.plugin.Plugin;

import java.util.Collections;

/**
 * @author Yoav Aharoni
 */
public class JavaScriptFeedbackPlugin extends Plugin {
	private Component defaultFeedbackContainer;

	public JavaScriptFeedbackPlugin() {
		setModel(newFeedbackMessagesModel());
		setOutputMarkupId(true);
	}

	public static String asJsStringParam(Object param) {
		return '\'' + param.toString()
				.replaceAll("\\\\", "\\\\\\\\")
				.replaceAll("'", "\\\\'")
				.replaceAll("\n", "<br/>")
				.replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
				.replaceAll("\r", "") + '\'';
	}

	/**
	 * @see org.apache.wicket.Component#isVersioned()
	 */
	@Override
	public boolean isVersioned() {
		return false;
	}

	public Component getDefaultFeedbackContainer() {
		return defaultFeedbackContainer;
	}

	public void setDefaultFeedbackContainer(Component defaultFeedbackContainer) {
		this.defaultFeedbackContainer = defaultFeedbackContainer;
	}

	@SuppressWarnings({"unchecked"})
	protected final java.util.List<FeedbackMessage> getCurrentMessages() {
		java.util.List<FeedbackMessage> messages = (java.util.List<FeedbackMessage>) getModelObject();
		return Collections.unmodifiableList(messages);
	}

	protected FeedbackMessagesModel newFeedbackMessagesModel() {
		return new FeedbackMessagesModel(this);
	}

	private String getScript() {
		StringBuffer initScript = new StringBuffer();

		// init
		String defaultFeedbackId = defaultFeedbackContainer == null
				? "" : defaultFeedbackContainer.getMarkupId();

		initScript
				.append("(function(fd) {\n")
				.append("fd.init('")
				.append(defaultFeedbackId)
				.append("');\n");

		// add messages
		for (FeedbackMessage message : getCurrentMessages()) {
			message.markRendered();
			initScript
					.append("fd.addMessage('")
					.append(getMarkupIdFor(message.getReporter()))
					.append("', '")
					.append(message.getLevelAsString())
					.append("', ")
					.append(asJsStringParam(message.getMessage()))
					.append(");\n");
		}

		initScript.append("fd.showMessages();\n");
		initScript.append("})(FeedbackDistributer);\n");
		return initScript.toString();
	}

	private String getMarkupIdFor(final Component component) {
		Component current = component;

		// find first parent with markup id
		while (current != null) {
			if (current.getOutputMarkupId() &&
					(!current.getRenderBodyOnly() || current.getOutputMarkupPlaceholderTag())) {
				return current.getMarkupId();
			}

			current = current.getParent();
		}
		return "";
	}
}
