package org.wtk.component.container;

import org.apache.wicket.markup.html.WebMarkupContainer;

/**
 * @author Yoav Aharoni
 */
public class PlaceHolder extends WebMarkupContainer {
    public PlaceHolder(String id) {
        super(id);
        setVisible(false);
        setOutputMarkupPlaceholderTag(true);
    }
}
