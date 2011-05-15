package org.wtk.behavior.head;

import org.apache.wicket.behavior.AbstractBehavior;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.string.Strings;

/**
 * @author Yoav Aharoni
 */
public class PageTitle extends AbstractBehavior {
	private IModel titleModel;

	public PageTitle(IModel titleModel) {
		this.titleModel = titleModel;
	}

	public PageTitle(String title) {
		this(new Model<String>(title));
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		if (titleModel != null) {
			Object title = titleModel.getObject();
			if (title != null) {
				response.renderString(String.format("<title>%s</title>\n", Strings.escapeMarkup(title.toString())));
			}
		}
	}
}
