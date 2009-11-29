package org.wtk.behavior;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * @author Yoav Aharoni
 */
public class CssStyle extends AttributeAppender {
    public CssStyle(final String attribute, final String value) {
        super("style", true, new Model(attribute + ":" + value), ";");
    }

    public CssStyle(final String attribute, final IModel model) {
        super("style", true, new AbstractReadOnlyModel() {
            @Override
            public Object getObject() {
                return attribute + ":" + model.getObject();
            }
        }, ";");
    }

}
