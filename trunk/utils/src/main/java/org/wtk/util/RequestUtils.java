package org.wtk.util;

import org.apache.wicket.Page;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.protocol.http.WebRequest;
import org.apache.wicket.protocol.http.WebRequestCycle;
import org.apache.wicket.protocol.http.WebResponse;

/**
 * @author Yoav Aharoni
 */
public class RequestUtils {
    public static AjaxRequestTarget getAjaxRequestTarget() {
        return AjaxRequestTarget.get();
    }

    public static boolean isAjaxRequest() {
        return getAjaxRequestTarget() != null;
    }

    public static String urlFor(Class pageClass) {
        return RequestCycle.get().urlFor(pageClass, null).toString();
    }

    public static WebRequest getWebRequest() {
        WebRequestCycle webRequestCycle = (WebRequestCycle) WebRequestCycle.get();
        if (webRequestCycle == null) {
            return null;
        }
        return webRequestCycle.getWebRequest();
    }

    public static WebResponse getWebResponse() {
        WebRequestCycle webRequestCycle = (WebRequestCycle) WebRequestCycle.get();
        if (webRequestCycle == null) {
            return null;
        }
        return webRequestCycle.getWebResponse();
    }

    public static Page getResponsePage() {
        return RequestCycle.get().getResponsePage();
    }
}
