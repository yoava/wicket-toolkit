package org.wtk.resource;

import org.apache.wicket.Page;
import org.wtk.util.JSBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yoav Aharoni
 */
public abstract class PageInjectorJsResource extends BaseInjectorJsResource {

	protected abstract Page createPage();

	protected final String getJavaScriptFilename() {
		return "PageInjectorJsResource.js";
	}

	public String getContentMarker() {
		return "<WTK:BODY xmlns=\"\"></WTK:BODY>";
	}

	protected Map<String, String> getScriptParameters() throws Exception {
		Map<String, String> params = new HashMap<String, String>();

		Page page = createPage();
		final String html = renderPage(page);
		final String head = getInnerHtml(html, "head");
		params.put("head", JSBuilder.serialize(head));

		String body = getInnerHtml(html, "body");
		final int contentIndex = body.indexOf(getContentMarker());
		if (contentIndex < 0) {
			params.put("header", JSBuilder.serialize(body));
			params.put("footer", "''");
		} else {
			final String header = body.substring(0, contentIndex);
			params.put("header", JSBuilder.serialize(header));

			final String footer = body.substring(contentIndex + getContentMarker().length());
			params.put("footer", JSBuilder.serialize(footer));
		}
		return params;
	}

}
