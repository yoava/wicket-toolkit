package org.wtk.util.ajax;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.wtk.util.JSBuilder;

/**
 * @author Yoav Aharoni
 */
public class AjaxConfirm {
	private static final AjaxConfirm INSTANCE = new AjaxConfirm();

	private AjaxConfirm() {
	}

	public void confirm(ConfirmDialog dialog) {
		final ConfirmAjaxBehavior eventBehavior = new ConfirmAjaxBehavior(dialog);
		RequestCycle.get().getResponsePage().add(eventBehavior);
		final AjaxRequestTarget target = AjaxRequestTarget.get();
		target.appendJavascript(eventBehavior.getConfirmScript());
	}

	private static class ConfirmAjaxBehavior extends AjaxEventBehavior {
		private final ConfirmDialog dialog;

		private ConfirmAjaxBehavior(ConfirmDialog dialog) {
			super("wtk:confirm");
			this.dialog = dialog;
		}

		public String getConfirmScript() {
			return getCallbackScript().toString();
		}

		@Override
		protected void onEvent(AjaxRequestTarget target) {
			String approvedString = RequestCycle.get().getRequest().getParameter("confirm");
			boolean approved = StringUtils.isEmpty(approvedString) || Boolean.valueOf(approvedString);
			dialog.onConfirm(approved, target);
			getComponent().remove(this);
		}

		@Override
		protected CharSequence generateCallbackScript(CharSequence partialCall) {
			return new JSBuilder().append("var ok=")
					.call("confirm", dialog.getMessage()).append(';')
					.append(super.generateCallbackScript(partialCall + "+'&confirm=' + ok"));
		}
	}

	public static AjaxConfirm get() {
		return INSTANCE;
	}
}
