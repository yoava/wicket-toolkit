package org.wtk.component.panel.border.titled;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wtk.component.panel.base.BasePanel;
import org.wtk.model.Titled;

/**
 * @author Yoav Aharoni
 */
public class TitledPanel extends BasePanel<String> implements Titled {
	public TitledPanel() {
	}

	public TitledPanel(String id) {
		super(id);
	}

	@Override
	public String getTitle() {
		return getDefaultModelObjectAsString();
	}

	public TitledPanel setTitle(String title) {
		setModel(new Model<String>(title));
		return this;
	}

	public TitledPanel setTitle(IModel<String> titleModel) {
		setModel(titleModel);
		return this;
	}

	public TitledPanel setTitleResource(String titleKey) {
		setModel(resourceModel(titleKey));
		return this;
	}
}