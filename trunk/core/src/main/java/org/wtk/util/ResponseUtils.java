package org.wtk.util;

import org.apache.wicket.Page;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.Response;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.protocol.http.WebRequestCycle;
import org.apache.wicket.protocol.http.WebResponse;
import org.apache.wicket.util.string.JavascriptUtils;
import org.wtk.application.CurrentPageSupport;

/**
 * @author Yoav Aharoni
 */
public class ResponseUtils {
	public static void renderJavaScript(String javascript) {
		final AjaxRequestTarget target = AjaxRequestTarget.get();
		if (target != null) {
			target.appendJavascript(javascript);
		} else {
			final Response response = RequestCycle.get().getResponse();
			JavascriptUtils.writeJavascript(response, javascript);
		}
	}

	public static Page getResponsePage() {
		return CurrentPageSupport.getCurrentPage();
	}

	public static WebResponse getWebResponse() {
		WebRequestCycle webRequestCycle = (WebRequestCycle) WebRequestCycle.get();
		if (webRequestCycle == null) {
			return null;
		}
		return webRequestCycle.getWebResponse();
	}
}
