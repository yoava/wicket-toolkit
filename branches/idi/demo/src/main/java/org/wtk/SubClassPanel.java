package org.wtk;

import org.apache.wicket.markup.html.basic.Label;

/**
 * @author Yoav Aharoni
 */
public class SubClassPanel extends DemoPanel {
    public SubClassPanel(String id) {
        super(id);
        add(new Label("child", "I'm the child"));
    }
}
