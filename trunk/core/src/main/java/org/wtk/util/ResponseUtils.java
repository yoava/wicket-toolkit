package org.wtk.util;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebRequestCycle;
import org.apache.wicket.protocol.http.WebResponse;
import org.wtk.application.CurrentPageSupport;

/**
 * @author Yoav Aharoni
 */
public class ResponseUtils {
	public static Page getCurrentPage() {
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
