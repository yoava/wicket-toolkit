package org.wtk;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

public class DemoApplication extends WebApplication {

	@Override
	public void sessionDestroyed(String sessionId) {
		super.sessionDestroyed(sessionId);
	}

	@Override
	public Class<? extends Page> getHomePage() {
		return DemoIndexPage.class;
	}

	@Override
	protected void init() {
		super.init();
	}
}
