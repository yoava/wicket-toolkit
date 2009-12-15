package org.wtk.behavior.javascript;

import org.apache.wicket.Component;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.Response;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.AbstractBehavior;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.string.JavascriptUtils;

/**
 * @author Yoav Aharoni
 */
public class RenderJavaScript extends AbstractBehavior {
	private IModel javascript;

	public RenderJavaScript(String javascript) {
		this(new Model(javascript));
	}

	public RenderJavaScript(IModel javascript) {
		this.javascript = javascript;
	}

	public String getJavascript() {
		return javascript.getObject().toString();
	}

	@Override
	public void onRendered(Component component) {
		super.onRendered(component);
		final AjaxRequestTarget target = AjaxRequestTarget.get();
		if (target != null) {
			target.appendJavascript(getJavascript());
		} else {
			final Response response = RequestCycle.get().getResponse();
			JavascriptUtils.writeJavascript(response, getJavascript());
		}
	}
}
