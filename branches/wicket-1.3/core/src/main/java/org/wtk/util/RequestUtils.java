package org.wtk.util;

import org.apache.wicket.RequestCycle;
import org.apache.wicket.protocol.http.WebRequest;
import org.apache.wicket.protocol.http.WebRequestCycle;

/**
 * @author Yoav Aharoni
 */
public class RequestUtils {
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
}
