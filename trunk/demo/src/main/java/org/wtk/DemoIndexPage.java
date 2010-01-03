package org.wtk;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.TextField;
import org.wtk.component.page.BasePage;
import org.wtk.feedback.behavior.FeedbackTargetBehavior;
import org.wtk.feedback.handler.AlertFeedbackHandler;
import org.wtk.feedback.handler.DistributedFeedbackHandler;
import org.wtk.jquery.behavior.feedback.FieldMarkerFeedbackHandler;
import org.wtk.util.Ajax;

/**
 * Homepage
 */
public class DemoIndexPage extends BasePage {
	public DemoIndexPage() {
		add(FieldMarkerFeedbackHandler.get());
		add(DistributedFeedbackHandler.get());
		add(AlertFeedbackHandler.get());


//		new DemoDialog().show();

//        wrap(new StyledBorderPanel());
//        getBodyContainer().add(new CssClass("test"));
//        add(new SubClassPanel("panel").wrap(new TitledPanel("MY TITLE")).wrap(new StyledBorderPanel()));
		add(new AjaxLink("test") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				new DemoDialog().show();
			}
		});


		final WebMarkupContainer feedback = new WebMarkupContainer("feedback");
		add(feedback);

		final TextField field = new TextField("text");
		field.add(new AjaxEventBehavior("ondblclick") {
			@Override
			protected void onEvent(AjaxRequestTarget target) {
				Ajax.Feedback.refreshFeedback();
			}
		});
		field.setOutputMarkupId(true);
		add(field);
		field.error("[[error]] Test");
		field.debug("debug Test");
		field.info("info Test");

		field.add(new FeedbackTargetBehavior(feedback));
	}
}
