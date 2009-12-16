package org.wtk;

import org.apache.wicket.protocol.http.WebApplication;
import org.wtk.application.CurrentPageSupport;

public class DemoApplication extends WebApplication {

	@Override
	public void sessionDestroyed(String sessionId) {
		super.sessionDestroyed(sessionId);

	}

	@Override
	public Class getHomePage() {
		return DemoIndexPage.class;
	}

	@Override
	protected void init() {
		super.init();
		addComponentInstantiationListener(new CurrentPageSupport());
	}
}
