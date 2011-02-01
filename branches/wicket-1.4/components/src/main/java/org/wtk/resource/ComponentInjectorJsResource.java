package org.wtk.resource;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.wtk.component.page.wrapper.ComponentWrapperPage;
import org.wtk.util.JSBuilder;

import java.util.HashMap;
import java.util.Map;

import static org.wtk.component.list.ComponentListView.ITEM_ID;

/**
 * @author Yoav Aharoni
 */
public abstract class ComponentInjectorJsResource extends BaseInjectorJsResource {
	private String jsObjectName;

	protected ComponentInjectorJsResource(String jsObjectName) {
		this.jsObjectName = jsObjectName;
	}

	public String getJsObjectName() {
		return jsObjectName;
	}

	protected abstract Component createComponent(String id);

	protected Page createPage() {
		return new ComponentWrapperPage();
	}

	protected final String getJavaScriptFilename() {
		return "ComponentInjectorJsResource.js";
	}

	protected Map<String, String> getScriptParameters() throws Exception {
		Page page = createPage();
		page.add(createComponent(ITEM_ID));

		final String html = renderPage(page);
		String head = getInnerHtml(html, "head");
		String body = getInnerHtml(html, "body");

		Map<String, String> params = new HashMap<String, String>();
		params.put("head", JSBuilder.serialize(head));
		params.put("body", JSBuilder.serialize(body));
		params.put("objectName", getJsObjectName());
		return params;
	}
}
