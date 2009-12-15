package org.wtk.component.panel.border.titled;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wtk.api.Titled;
import org.wtk.component.label.TitleLabel;
import org.wtk.component.panel.base.BasePanel;

import java.io.Serializable;

import static org.wtk.util.ResourceUtils.defaultResource;

/**
 * @author Yoav Aharoni
 */
public class TitledPanel<T extends Serializable> extends BasePanel<T> implements Titled {
    public TitledPanel(String title) {
        this("TitledPanel", defaultResource(title));
    }

    public TitledPanel(IModel titleModel) {
        this("TitledPanel", titleModel);
    }

    public TitledPanel(String id, String title) {
        this(id, new Model(title));
    }

    public TitledPanel(String id, IModel titleModel) {
        super(id, titleModel);
        add(new TitleLabel("title", this));
    }

    @Override
    public String getTitle() {
        return getModelObjectAsString();
    }
}