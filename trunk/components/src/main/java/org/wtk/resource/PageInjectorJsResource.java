package org.wtk.resource;

import org.apache.wicket.Page;
import org.w3c.dom.Document;
import org.wtk.util.JSBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tomer Cohen
 */
public abstract class PageInjectorJsResource extends BaseInjectorJsResource {

	protected abstract Page createPage();

	public String getJavaScriptFilename() {
		return "PageInjectorJsResource.js";
	}

	protected Map<String, String> getScriptParameters() throws Exception {
		Page page = createPage();
		Document document = getPageDocument(page);
		fixRelativeUrls(document);

		String html = serializeDocument(document);
		String head = getInnerHtml(html, "head");
		String body = getInnerHtml(html, "body");
		String[] bodyParts = body.split("\\$\\{content\\}");

		Map<String, String> params = new HashMap<String, String>();
		params.put("head", new JSBuilder().commaList(head).toString());
		params.put("header", new JSBuilder().commaList(bodyParts[0]).toString());
		params.put("footer", new JSBuilder().commaList(bodyParts[1]).toString());
		return params;
	}
}
