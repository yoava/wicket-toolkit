package org.wtk.jquery.ui.dialog;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.wtk.behavior.CssClass;
import org.wtk.behavior.head.HeadResource;
import org.wtk.component.list.ItemsContainer;
import org.wtk.component.support.plugin.Plugin;
import org.wtk.jquery.resource.JQueryUIHeadResource;
import org.wtk.util.JavaScriptUtils;

import java.util.Iterator;

/**
 * @author Yoav Aharoni
 */
public class JQueryDialogPlugin extends Plugin {
	private static final String CONTAINER_ID = "dialogs";

	public JQueryDialogPlugin() {
		add(new HeadResource(JQueryDialogPlugin.class).addJavaScript()
				.dependsOn(new JQueryUIHeadResource()));

		add(new ItemsContainer<JQueryDialog>(CONTAINER_ID).add(new CssClass("wtk-jq-dialogs")));
	}

	@Override
	protected void onAfterRender() {
		super.onAfterRender();
		final AjaxRequestTarget target = AjaxRequestTarget.get();
		if (target != null) {
			target.appendJavascript("jQuery.wtk.dialog.cleanup();");
		}
		JavaScriptUtils.render(getRenderScript());
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

	@SuppressWarnings({"unchecked"})
	private ItemsContainer<JQueryDialog> getDialogContainer() {
		return (ItemsContainer<JQueryDialog>) get(CONTAINER_ID);
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
