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
public final class Ajax {
	private Ajax() {
	}

	public static void render(Component... components) {
		final AjaxRequestTarget target = AjaxRequestTarget.get();
		for (Component component : components) {
			target.addComponent(component);
		}
	}

	public static void prependJavascript(CharSequence script) {
		AjaxRequestTarget.get().prependJavascript(script.toString());
	}

	public static void prependJavascript(String script, String... params) {
		AjaxRequestTarget.get().prependJavascript(String.format(script, params));
	}

	public static void appendJavascript(CharSequence script) {
		AjaxRequestTarget.get().appendJavascript(script.toString());
	}

	public static void appendJavascript(String script, String... params) {
		AjaxRequestTarget.get().appendJavascript(String.format(script, params));
	}

	public static AjaxRequestTarget getTarget() {
		return AjaxRequestTarget.get();
	}

	public static boolean isAjaxRequest() {
		return getTarget() != null;
	}

	public static final class Feedback {
		private Feedback() {
		}

		public static void refreshFeedback() {
			Page page = RequestCycle.get().getResponsePage();
			refreshFeedback(page);
		}

		public static void refreshFeedback(MarkupContainer container) {
			final AjaxRequestTarget target = getTarget();
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
}
