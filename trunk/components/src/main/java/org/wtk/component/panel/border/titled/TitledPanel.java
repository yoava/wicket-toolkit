package org.wtk.component.panel.border.titled;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wtk.api.Titled;
import org.wtk.component.panel.base.BasePanel;

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
		return getModelObjectAsString();
	}

	public TitledPanel setTitle(String title) {
		setModel(new Model(title));
		return this;
	}

	public TitledPanel setTitle(IModel titleModel) {
		setModel(titleModel);
		return this;
	}

	public TitledPanel setTitleResource(String titleKey) {
		setModel(resourceModel(titleKey));
		return this;
	}
}