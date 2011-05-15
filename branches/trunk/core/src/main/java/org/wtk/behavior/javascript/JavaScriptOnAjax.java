package org.wtk.behavior.javascript;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.wtk.behavior.BaseHeadBehavior;
import org.wtk.util.Ajax;

/**
 * @author Yoav Aharoni
 */
public class JavaScriptOnAjax extends BaseHeadBehavior<String> {
	public JavaScriptOnAjax(IModel javascriptModel) {
		super(javascriptModel);
	}

	public JavaScriptOnAjax(String javascript) {
		super(javascript);
	}

	@Override
	public void beforeRender(Component component) {
		Ajax.appendJavascript(getModelObject());
	}
}