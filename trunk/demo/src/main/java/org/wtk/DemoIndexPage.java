package org.wtk;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.wtk.component.page.BasePage;
import org.wtk.jquery.model.JQuery;

import static org.wtk.jquery.model.JQuery.Effect;
import static org.wtk.jquery.model.JQuery.Position.BOTTOM;
import static org.wtk.jquery.model.JQuery.Position.LEFT;

/**
 * Homepage
 */
public class
		DemoIndexPage extends BasePage {
	public DemoIndexPage() {

//        wrap(new StyledBorderPanel());
//        getBodyContainer().add(new CssClass("test"));
//        add(new SubClassPanel("panel").wrap(new TitledPanel("MY TITLE")).wrap(new StyledBorderPanel()));
		add(new AjaxLink("test") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				new DemoDialog().setPosition(BOTTOM, LEFT).setShowEffect(Effect.EXPLODE).setHideEffect(Effect.EXPLODE).setTitle("Title").setModal(true).setTheme(JQuery.Theme.UI_LIGHTNESS).show();
			}
		});
	}
}
