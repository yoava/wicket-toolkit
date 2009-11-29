package org.wtk.behavior;

import org.apache.wicket.behavior.AbstractBehavior;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.util.string.Strings;

/**
 * @author Yoav Aharoni
 */
public class MetaBehavior extends AbstractBehavior {
    private CharSequence name;
    private CharSequence value;

    public MetaBehavior(String name, String value) {
        this.name = Strings.escapeMarkup(name);
        this.value = Strings.escapeMarkup(value);
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        response.renderString(String.format("<meta name=\"%s\" content=\"%s\"/>\n", name, value));
    }
}