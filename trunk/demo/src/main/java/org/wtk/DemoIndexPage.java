package org.wtk;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.wtk.component.page.BasePage;
import org.wtk.component.support.plugin.PluginManager;
import org.wtk.jquery.model.JQuery;
import org.wtk.jquery.resource.JQuerySkin;
import org.wtk.jquery.ui.dialog.JQueryDialogPlugin;

/**
 * Homepage
 */
public class
		DemoIndexPage extends BasePage {
	public DemoIndexPage() {
		add(new JQuerySkin(JQuery.Theme.UI_LIGHTNESS));

		PluginManager.get(this).getPlugin(JQueryDialogPlugin.class).show(new DemoDialog());
//        wrap(new StyledBorderPanel());
//        getBodyContainer().add(new CssClass("test"));
//        add(new SubClassPanel("panel").wrap(new TitledPanel("MY TITLE")).wrap(new StyledBorderPanel()));
		add(new AjaxLink("test") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				new DemoDialog().show();
			}
		});
	}
}
