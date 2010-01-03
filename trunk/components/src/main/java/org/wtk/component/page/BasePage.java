package org.wtk.component.page;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.Response;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.wtk.api.Titled;
import org.wtk.behavior.head.MetaHttpEquiv;
import org.wtk.behavior.head.PageTitle;
import org.wtk.component.container.TrasparentContainer;
import org.wtk.component.panel.base.BasePanel;
import org.wtk.component.support.border.BorderSupport;
import org.wtk.component.support.border.IBorderSupport;
import org.wtk.component.support.plugin.PluginManager;
import org.wtk.model.annotation.WicketProperty;

/**
 * @author Yoav Aharoni
 */
public class BasePage extends WebPage implements Titled, IBorderSupport<BasePage, BasePanel> {
	private static final String BODY_ID = "body";
	private static final String BORDER_ID = "wtk-internal-border";

	public BasePage() {
		MarkupContainer body = newBodyContainer(BODY_ID);
		add(body);
		body.add(new BorderSupport(BORDER_ID));
		add(new PluginManager("pluginManager"));

		addHeaderContributors();
	}

	@Override
	public BasePage replaceBorder(BasePanel borderPanel) {
		getBorderSupport().replaceBorder(borderPanel);
		return this;
	}

	@Override
	public BasePage removeBorder() {
		getBorderSupport().removeBorder();
		return this;
	}

	@Override
	public BasePage wrap(BasePanel borderPanel) {
		getBorderSupport().wrap(borderPanel);
		return this;
	}

	@Override
	public BasePage wrapOuterBorder(BasePanel borderPanel) {
		getBorderSupport().wrapOuterBorder(borderPanel);
		return this;
	}

	@Override
	public BasePanel getBorder() {
		return getBorderSupport().getBorder();
	}

	@Override
	public String getTitle() {
		return getString("page.title", new Model(this));
	}

	@WicketProperty
	public String getEncoding() {
		return getApplication().getRequestCycleSettings().getResponseRequestEncoding();
	}

	public MarkupContainer getBodyContainer() {
		return (MarkupContainer) get(BODY_ID);
	}

	public PluginManager getPluginManager() {
		return (PluginManager) get("pluginManager");
	}

	public String getContentType() {
		return getString("page.contentType", new Model(this));
	}

	public String getDocTypeDeclaration() {
		return getString("page.docTypeDeclaration", new Model(this));
	}

	public String getXmlDeclaration() {
		return getString("page.xmlDeclaration", new Model(this));
	}

	@Override
	protected void onRender(MarkupStream markupStream) {
		final String xmlDeclaration = getXmlDeclaration();
		Response response = getResponse();
		if (StringUtils.isNotEmpty(xmlDeclaration)) {
			response.write(xmlDeclaration);
			response.write("\n");
		}

		final String docTypeDeclaration = getDocTypeDeclaration();
		if (StringUtils.isNotEmpty(docTypeDeclaration)) {
			response.write(docTypeDeclaration);
			response.write("\n");
		}

		super.onRender(markupStream);
	}

	@SuppressWarnings({"unchecked"})
	private IBorderSupport<IBorderSupport, BasePanel> getBorderSupport() {
		return (IBorderSupport<IBorderSupport, BasePanel>) getBodyContainer().get(BORDER_ID);
	}

	private void addHeaderContributors() {
		add(new MetaHttpEquiv("Content-Type", getContentType()));
		add(new PageTitle(new PropertyModel(this, "title")));
	}

	protected MarkupContainer newBodyContainer(String id) {
		return new TrasparentContainer(id);
	}
}
