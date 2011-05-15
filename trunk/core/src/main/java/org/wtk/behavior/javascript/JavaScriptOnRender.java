package org.wtk.behavior.javascript;

import org.apache.wicket.Component;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.Response;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.string.JavascriptUtils;
import org.wtk.behavior.BaseHeadBehavior;

/**
 * @author Yoav Aharoni
 */
public class JavaScriptOnRender extends BaseHeadBehavior<String> {
	public JavaScriptOnRender(IModel javascriptModel) {
		super(javascriptModel);
	}

	public JavaScriptOnRender(String javascript) {
		super(javascript);
	}

	@Override
	public void onRendered(Component component) {
		final AjaxRequestTarget target = AjaxRequestTarget.get();
		if (target != null) {
			target.appendJavascript(getModelObject());
		} else {
			final Response response = RequestCycle.get().getResponse();
			JavascriptUtils.writeJavascript(response, getModelObject());
		}
	}
}
