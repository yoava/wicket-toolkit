package org.wtk.behavior.head;

import org.apache.wicket.behavior.AbstractBehavior;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.util.string.Strings;

/**
 * @author Yoav Aharoni
 */
public class MetaHttpEquiv extends AbstractBehavior {
	private CharSequence name;
	private CharSequence value;

	public MetaHttpEquiv(String name, String value) {
		this.name = Strings.escapeMarkup(name);
		this.value = Strings.escapeMarkup(value);
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		response.renderString(String.format("<meta http-equiv=\"%s\" content=\"%s\"/>\n", name, value));
	}
}
