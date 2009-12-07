package org.wtk;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.form.Form;
import org.wtk.component.support.plugin.PluginManager;
import org.wtk.jquery.model.JQuery;
import org.wtk.jquery.ui.dialog.JQueryDialog;
import org.wtk.jquery.ui.dialog.JQueryDialogCloseLink;

/**
 * @author Yoav Aharoni
 */
public class DemoDialog extends JQueryDialog {
	public DemoDialog() {
		wrap(new Form(getId()));
		setModal(true);
		setTitle("Demo-" + getSession().nextSequenceValue());
		setShowEffect(JQuery.Effect.PUFF);
		setHideEffect(JQuery.Effect.PUFF);
		add(new AjaxLink("new") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				new DemoDialog().show();
			}
		});
		add(new AjaxLink("refresh") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				target.addComponent((Component) PluginManager.get());
			}
		});
		add(new JQueryDialogCloseLink("close"));
	}
}
