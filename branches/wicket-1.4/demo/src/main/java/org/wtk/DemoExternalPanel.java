package org.wtk;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.resource.IResourceStream;
import org.wtk.util.markup.ExternalMarkup;
import org.wtk.util.markup.ExternalMarkupSupport;

/**
 * @author Yoav Aharoni
 */
public class DemoExternalPanel extends Panel implements ExternalMarkup {
	public static final ExternalMarkupSupport EXTERNAL_MARKUP = new ExternalMarkupSupport(DemoExternalPanel.class, "file:///D:/Temp/markup.html");

	public DemoExternalPanel(String id) {
		super(id);
	}

	@Override
	public String getCacheKey(MarkupContainer container, Class containerClass) {
		return EXTERNAL_MARKUP.getCacheKey(container, containerClass);
	}

	@Override
	public IResourceStream getMarkupResourceStream(MarkupContainer container, Class containerClass) {
		return EXTERNAL_MARKUP.getMarkupResourceStream(container, containerClass);
	}
}
