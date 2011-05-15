package org.wtk.behavior.ajax;

import org.apache.wicket.ajax.AjaxEventBehavior;

/**
 * @author Yoav Aharoni
 */
public abstract class EventlessAjaxBehavior extends AjaxEventBehavior {
	public EventlessAjaxBehavior() {
		super("wtk:ajax");
	}

	public String getCallFunction() {
		return String.format("function() {%s}", getCallbackScript());
	}

	public String getSafeCallScript() {
		return String.format("(%s)();", getCallFunction());
	}

	public String getCallScript() {
		return getCallbackScript().toString();
	}
}
