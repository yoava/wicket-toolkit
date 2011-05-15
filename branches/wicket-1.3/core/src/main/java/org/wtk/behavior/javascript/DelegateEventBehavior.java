package org.wtk.behavior.javascript;

import org.apache.wicket.Component;
import org.apache.wicket.model.AbstractReadOnlyModel;

/**
 * @author Yoav Aharoni
 */
public class DelegateEventBehavior extends JavascriptEvent {
	public DelegateEventBehavior(final String event, final Component delegate) {
		super(event, new AbstractReadOnlyModel() {
			@Override
			public Object getObject() {
				return "document.getElementById('" + delegate.getMarkupId() + "')." + event + "(event);";
			}
		});
		delegate.setOutputMarkupId(true);
	}
}
