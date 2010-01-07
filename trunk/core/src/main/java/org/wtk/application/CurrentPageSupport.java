package org.wtk.application;

import org.apache.wicket.Component;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.Page;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.application.IComponentInstantiationListener;
import org.wtk.behavior.BaseBehavior;

/**
 * @author Yoav Aharoni
 */
public class CurrentPageSupport implements IComponentInstantiationListener {
	private static final MetaDataKey CURRENT_PAGE = new MetaDataKey(Page.class) {
	};

	@Override
	public void onInstantiation(Component component) {
		if (component instanceof Page) {
			component.add(new UpdateCurrentPageBehavior());
			setCurrentPage((Page) component);
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

	public static void setCurrentPage(Page page) {
		final RequestCycle requestCycle = RequestCycle.get();
		if (requestCycle != null) {
			requestCycle.setMetaData(CURRENT_PAGE, page);
		}
	}

	private static class UpdateCurrentPageBehavior extends BaseBehavior {
		@Override
		public void beforeRender(Component component) {
			final Page page = (Page) component;
			setCurrentPage(page);
		}

		@Override
		public void afterRender(Component component) {
			setCurrentPage(null);
		}

		@Override
		public void exception(Component component, RuntimeException exception) {
			setCurrentPage(null);
		}
	}
}
