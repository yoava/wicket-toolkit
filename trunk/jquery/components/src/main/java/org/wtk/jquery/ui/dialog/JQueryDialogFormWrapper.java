package org.wtk.jquery.ui.dialog;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;

import static java.lang.String.format;

/**
 * @author Yoav Aharoni
 */
public class JQueryDialogFormWrapper extends JQueryDialog {
	public JQueryDialogFormWrapper(JQueryDialog dialog, Form form) {
		add(form);
		form.add(dialog);
	}

	@Override
	public String getShowScript() {
		return format("jQuery('#%s').wtk('dialog','show',%s);", getMarkupId(), getDialog().getOptionsJson());
	}

	@Override
	protected void onCloseButtonClicked(AjaxRequestTarget target) {
		super.onCloseButtonClicked(target);
		getDialog().onCloseButtonClicked(target);
	}

	@Override
	protected void onShow(AjaxRequestTarget target) {
		super.onShow(target);
		getDialog().onShow(target);
	}

	@Override
	protected void onClose(AjaxRequestTarget target) {
		super.onClose(target);
		getDialog().onClose(target);
	}

	public JQueryDialog getDialog() {
		return (JQueryDialog) get("item:item");
	}
}
