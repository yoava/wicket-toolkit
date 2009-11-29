package org.wtk;

import org.apache.wicket.protocol.http.WebApplication;

public class DemoApplication extends WebApplication {

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public Class getHomePage() {
        return DemoIndexPage.class;
    }
}
