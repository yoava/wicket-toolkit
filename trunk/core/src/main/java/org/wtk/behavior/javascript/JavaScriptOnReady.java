package org.wtk.behavior.javascript;

import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.model.IModel;
import org.wtk.behavior.BaseHeadBehavior;

/**
 * @author Yoav Aharoni
 */
public class JavaScriptOnReady extends BaseHeadBehavior<String> {
	public JavaScriptOnReady(IModel javascriptModel) {
		super(javascriptModel);
	}

	public JavaScriptOnReady(String javascript) {
		super(javascript);
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		response.renderOnDomReadyJavascript(getModelObject());
	}
}
