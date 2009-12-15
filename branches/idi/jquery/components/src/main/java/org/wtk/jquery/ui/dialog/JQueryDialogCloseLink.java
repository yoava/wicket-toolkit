package org.wtk.jquery.ui.dialog;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;

/**
 * @author Yoav Aharoni
 */
public class JQueryDialogCloseLink extends AjaxLink {
	private JQueryDialog dialog;

	public JQueryDialogCloseLink(String id) {
		super(id);
	}

	JQueryDialogCloseLink(String id, JQueryDialog dialog) {
		super(id);
		this.dialog = dialog;
	}

	@Override
	public void onClick(AjaxRequestTarget target) {
		if (dialog == null) {
			dialog = (JQueryDialog) findParent(JQueryDialog.class);
			if (dialog == null) {
				final String message = String.format("JQueryDialogCloseLink[id=%s] Can't find JQueryDialog to close.", getId());
				throw new RuntimeException(message);
			}
		}
		dialog.close();
	}
}
