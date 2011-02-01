package org.wtk;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.wtk.resource.ComponentInjectorJsResource;
import org.wtk.resource.PageInjectorJsResource;

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
        getSharedResources().add("page.js", new PageInjectorJsResource() {
            @Override
            protected Page createPage() {
                return new DemoIndexPage();
            }
        });

        getSharedResources().add("panel.js", new ComponentInjectorJsResource("WicketPanel") {
            @Override
            protected Component createComponent(String id) {
                return new DemoPanel(id);
            }
        });
    }
}
