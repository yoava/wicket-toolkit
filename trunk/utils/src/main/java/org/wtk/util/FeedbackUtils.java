package org.wtk.util;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.Page;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.feedback.IFeedback;

/**
 * @author Yoav Aharoni
 */
public class FeedbackUtils {
    public static void refreshFeedback() {
        Page page = RequestCycle.get().getResponsePage();
        refreshFeedback(page);
    }

    public static void refreshFeedback(MarkupContainer container) {
        final AjaxRequestTarget target = RequestUtils.getAjaxRequestTarget();
        if (container == null || target == null) {
            return;
        }
        container.visitChildren(IFeedback.class, new Component.IVisitor() {
            @Override
            public Object component(Component component) {
                if (component.getOutputMarkupId()) {
                    target.addComponent(component);
                }
                return CONTINUE_TRAVERSAL;
            }
        });
    }
}
