package org.wtk.behavior.ajax;

import org.apache.wicket.ajax.AjaxEventBehavior;

/**
 * @author Yoav Aharoni
 */
public abstract class AjaxEvent extends AjaxEventBehavior {
	public AjaxEvent() {
		super("wicket:ajax");
	}

	public String getScript() {
		return getCallbackScript().toString();
	}

	public String getFunction() {
		return String.format("function() {%s}", getScript());
	}
}
