package org.wtk.behavior.ajax;

import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.markup.html.form.Form;

/**
 * @author Yoav Aharoni
 */
public abstract class EventlessAjaxFormSubmitBehavior extends AjaxFormSubmitBehavior {
	public EventlessAjaxFormSubmitBehavior() {
		super("wtk:ajax");
	}

	protected EventlessAjaxFormSubmitBehavior(Form<?> form) {
		super(form, "wtk:ajax");
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