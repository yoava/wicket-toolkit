package org.wtk.component.panel.border.styled;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.IBehavior;
import org.apache.wicket.markup.ComponentTag;
import org.wtk.component.container.TrasparentContainer;
import org.wtk.component.panel.base.BasePanel;

import java.io.Serializable;

/**
 * @author Yoav Aharoni
 */
public class StyledBorderPanel<T extends Serializable> extends BasePanel<T> {
    public StyledBorderPanel() {
        this("StyledBorderPanel");
    }

    public StyledBorderPanel(String id) {
        super(id);

        add(new TrasparentContainer("styled-border"));
    }
}
