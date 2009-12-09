package org.wtk.util;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.util.JSONUtils;
import net.sf.json.util.WebUtils;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.Response;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.util.string.JavascriptUtils;

/**
 * @author Yoav Aharoni
 */
public class JavaScriptUtils {
	public static String jsFunctionCall(String functionName, Object... parameters) {
		final String params = jsParams(parameters);
		return functionName + "(" + params + ")";
	}

	public static String jsParam(Object parameter) {
		return JSONUtils.valueToString(parameter);
	}

	public static String jsParams(Object... parameters) {
		final JSONArray json = JSONArray.fromObject(parameters);
		return json.join(",");
	}

	public static String toString(JSON json) {
		return WebUtils.toString(json);
	}

	public static void render(String javascript) {
		final AjaxRequestTarget target = AjaxRequestTarget.get();
		if (target != null) {
			target.appendJavascript(javascript);
		} else {
			final Response response = RequestCycle.get().getResponse();
			JavascriptUtils.writeJavascript(response, javascript);
		}
	}
}
