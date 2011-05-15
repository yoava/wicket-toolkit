package org.wtk;

import org.apache.wicket.markup.html.basic.Label;

/**
 * @author Yoav Aharoni
 */
public class DemoChildPanel extends DemoExternalPanel {
	public DemoChildPanel(String id) {
		super(id);
		add(new Label("label", "Hello World!"));
	}
}
