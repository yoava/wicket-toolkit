package org.wtk.component.container;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;

/**
* @author Yoav Aharoni
*/
public class TrasparentContainer extends WebMarkupContainer {
    public TrasparentContainer(String id) {
        super(id);
    }

    public TrasparentContainer(String id, IModel model) {
        super(id, model);
    }

    @Override
    public boolean isTransparentResolver() {
        return true;
    }
}
