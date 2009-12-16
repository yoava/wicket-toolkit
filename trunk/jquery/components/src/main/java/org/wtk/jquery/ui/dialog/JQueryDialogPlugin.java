package org.wtk.jquery.ui.dialog;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.wtk.behavior.css.CssClass;
import org.wtk.behavior.head.HeadResource;
import org.wtk.component.list.ComponentListView;
import org.wtk.component.support.plugin.Plugin;
import org.wtk.jquery.resource.JQueryUIHeadResource;
import org.wtk.util.Ajax;

import java.util.Iterator;

import static org.wtk.util.ResponseUtils.renderJavaScript;

/**
 * @author Yoav Aharoni
 */
public class JQueryDialogPlugin extends Plugin {
	private static final String CONTAINER_ID = "dialogs";

	public JQueryDialogPlugin() {
		add(new HeadResource(JQueryDialogPlugin.class).addJavaScript()
				.dependsOn(new JQueryUIHeadResource()));

		add(new ComponentListView<JQueryDialog>(CONTAINER_ID).add(new CssClass("wtk-jq-dialogs")));
	}

	public void show(JQueryDialog dialog) {
		final JQueryDialog wrapper = dialog.getWrappingDialog();
		if (wrapper != null) {
			show(wrapper);
			return;
		}

		final AjaxRequestTarget target = AjaxRequestTarget.get();
		addDialog(dialog);
		dialog.onShow(target);
		if (target != null) {
			target.appendJavascript(dialog.getShowScript());
		}
	}

	public void close(JQueryDialog dialog) {
		final JQueryDialog wrapper = dialog.getWrappingDialog();
		if (wrapper != null) {
			close(wrapper);
			return;
		}

		final AjaxRequestTarget target = AjaxRequestTarget.get();
		if (target != null) {
			target.appendJavascript(dialog.getCloseScript());
		}
		dialog.onClose(target);
		removeDialog(dialog);
	}

	@Override
	protected void onAfterRender() {
		super.onAfterRender();
		Ajax.appendJavascript("jQuery.wtk.dialog.cleanup();");
		renderJavaScript(getRenderScript());
	}

	@SuppressWarnings({"unchecked"})
	private ComponentListView<JQueryDialog> getDialogContainer() {
		return (ComponentListView<JQueryDialog>) get(CONTAINER_ID);
	}

	private String getRenderScript() {
		StringBuilder script = new StringBuilder();
		final Iterator<JQueryDialog> dialogIterator = getDialogContainer().getItemsIterator();
		while (dialogIterator.hasNext()) {
			final JQueryDialog dialog = dialogIterator.next();
			script.append(dialog.getShowScript());
		}
		return script.toString();
	}

	protected void handleCloseButtonClick(JQueryDialog dialog, AjaxRequestTarget target) {
		final JQueryDialog wrapper = dialog.getWrappingDialog();
		if (wrapper != null) {
			handleCloseButtonClick(wrapper, target);
			return;
		}

		dialog.onCloseButtonClicked(target);
		dialog.onClose(target);
		removeDialog(dialog);
		target.appendJavascript(dialog.getDestroyScript());
	}

	protected void addDialog(JQueryDialog dialog) {
		getDialogContainer().addItem(dialog);
	}

	protected void removeDialog(JQueryDialog dialog) {
		getDialogContainer().internalRemoveItem(dialog);
	}
}
