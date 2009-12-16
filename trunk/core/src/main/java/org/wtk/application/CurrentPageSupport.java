package org.wtk.application;

import org.apache.wicket.Component;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.Page;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.application.IComponentInstantiationListener;

/**
 * @author Yoav Aharoni
 */
public class CurrentPageSupport implements IComponentInstantiationListener {
	private static final MetaDataKey CURRENT_PAGE = new MetaDataKey(Page.class) {
	};

	@Override
	public void onInstantiation(Component component) {
		if (component instanceof Page) {
			RequestCycle.get().setMetaData(CURRENT_PAGE, component);
		}
	}

	public static Page getCurrentPage() {
		final RequestCycle requestCycle = RequestCycle.get();
		if (requestCycle == null) {
			return null;
		}
		final Page current = (Page) requestCycle.getMetaData(CURRENT_PAGE);
		if (current != null) {
			return current;
		}
		return requestCycle.getResponsePage();
	}
}
