package org.wtk.feedback.dispatcher;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.wtk.behavior.head.HeadResource;
import org.wtk.component.support.plugin.PluginManager;
import org.wtk.util.JSBuilder;

/**
 * @author Yoav Aharoni
 */
public class BaseFeedbackHandler extends HeadResource {
	private String javascriptHandler;

	public BaseFeedbackHandler(Class<?> scope, String javascriptHandler) {
		super(scope);
		this.javascriptHandler = javascriptHandler;
		dependsOn(JsFeedbackDispatcherPlugin.HEAD_RESOURCE);
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.renderJavascript(getInitScript(), getScriptId());
	}

	@Override
	public void bind(Component component) {
		super.bind(component);
		PluginManager.get().getPlugin(JsFeedbackDispatcherPlugin.class);
	}

	private String getInitScript() {
		return String.format("wtk.feedback.addHandler(new wtk.feedback.%s(%s));", javascriptHandler, new JSBuilder().commaList(getParameters()));
	}

	protected String getScriptId() {
		return "feedback_" + javascriptHandler;
	}

	protected Object[] getParameters() {
		return null;
	}
}
