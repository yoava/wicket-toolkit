package org.wtk;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.wtk.component.page.BasePage;

/**
 * Homepage
 */
public class
		DemoIndexPage extends BasePage {
	public DemoIndexPage() {
		new DemoDialog().show();
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
