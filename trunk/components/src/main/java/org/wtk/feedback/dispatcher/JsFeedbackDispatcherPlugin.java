package org.wtk.feedback.dispatcher;

import org.apache.wicket.Component;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.FeedbackMessagesModel;
import org.apache.wicket.feedback.IFeedback;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.model.IModel;
import org.wtk.behavior.head.HeadResource;
import org.wtk.component.support.plugin.Plugin;
import org.wtk.resource.WicketToolkitHeadResource;
import org.wtk.util.JSBuilder;

import java.util.Iterator;
import java.util.List;

/**
 * @author Yoav Aharoni
 */
public class JsFeedbackDispatcherPlugin extends Plugin<List<FeedbackMessage>> implements IHeaderContributor, IFeedback {
	public static final HeadResource HEAD_RESOURCE = new HeadResource(JsFeedbackDispatcherPlugin.class)
			.dependsOn(WicketToolkitHeadResource.get())
			.addJavaScript();

	public JsFeedbackDispatcherPlugin() {
		setModel(newFeedbackMessagesModel());
		setOutputMarkupId(true);
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		response.renderOnDomReadyJavascript(getScript());
	}

	/**
	 * @see org.apache.wicket.Component#isVersioned()
	 */
	@Override
	public boolean isVersioned() {
		return false;
	}

	private String getScript() {
		JSBuilder initScript = new JSBuilder();

		initScript.startScope("f");
		initScript.append("f.dispatch([");

		for (Iterator<FeedbackMessage> iterator = getPluginModelObject().iterator(); iterator.hasNext();) {
			FeedbackMessage message = iterator.next();
			message.markRendered();
			final String id = getMarkupIdFor(message.getReporter());
			final String text = message.getMessage().toString();
			final String level = message.getLevelAsString();

			initScript.call("f.msg", id, text, level);
			if (iterator.hasNext()) {
				initScript.append(',');
			}
			initScript.append('\n');
		}

		initScript.append("]);");
		initScript.endScope("wtk.feedback");

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

	protected IModel<List<FeedbackMessage>> newFeedbackMessagesModel() {
		return new FeedbackMessagesModel(this);
	}
}
