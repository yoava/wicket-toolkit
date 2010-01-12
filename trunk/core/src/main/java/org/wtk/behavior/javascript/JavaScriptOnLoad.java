package org.wtk.behavior.javascript;

import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.model.IModel;
import org.wtk.behavior.BaseHeadBehavior;

/**
 * @author Yoav Aharoni
 */
public class JavaScriptOnLoad extends BaseHeadBehavior<String> {
	public JavaScriptOnLoad(IModel javascriptModel) {
		super(javascriptModel);
	}

	public JavaScriptOnLoad(String javascript) {
		super(javascript);
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		response.renderOnLoadJavascript(getModelObject());
	}
}