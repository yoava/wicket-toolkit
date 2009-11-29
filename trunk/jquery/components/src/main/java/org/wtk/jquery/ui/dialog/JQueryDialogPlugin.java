package org.wtk.jquery.ui.dialog;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.wtk.component.list.CollectionPanel;
import org.wtk.component.support.plugin.Plugin;
import org.wtk.jquery.resource.JQueryUIHeadResource;

import java.util.ArrayList;

/**
 * @author Yoav Aharoni
 */
public class JQueryDialogPlugin extends Plugin {
	private static final String CONTAINER_ID = "dialogs";

	public JQueryDialogPlugin() {
		add(new CollectionPanel<JQueryDialog>(CONTAINER_ID, new ArrayList<JQueryDialog>()));
		add(new JQueryUIHeadResource());
	}

	@SuppressWarnings({"unchecked"})
	private CollectionPanel<JQueryDialog> getDialogContainer() {
		return (CollectionPanel<JQueryDialog>) get(CONTAINER_ID);
	}

	public void show(JQueryDialog dialog) {
		getDialogContainer().addItem(dialog);
		final AjaxRequestTarget target = AjaxRequestTarget.get();
		dialog.onShow(target);
		target.appendJavascript(dialog.getShowScript());
	}

	public void close(JQueryDialog dialog) {
		final AjaxRequestTarget target = AjaxRequestTarget.get();
		dialog.onShow(target);
		dialog.onClose(AjaxRequestTarget.get());
		getDialogContainer().removeItem(dialog);
	}
}
