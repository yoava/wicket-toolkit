package org.wtk;

import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.basic.Label;
import org.wtk.component.panel.base.BasePanel;

/**
 * @author Yoav Aharoni
 */
public class DemoPanel extends BasePanel {
    public DemoPanel(String id) {
        super(id);
        add(new Label("title", "I'm the father") {
            @Override
            protected void onRender(MarkupStream markupStream) {
                super.onRender(markupStream);
            }
        });
    }
}