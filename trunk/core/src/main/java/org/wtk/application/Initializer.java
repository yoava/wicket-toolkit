package org.wtk.application;

import org.apache.wicket.Application;
import org.apache.wicket.IInitializer;

/**
 * @author Yoav Aharoni
 */
public class Initializer implements IInitializer {
	public void init(Application application) {
		application.addComponentInstantiationListener(new CurrentPageSupport());
	}

	public String toString() {
		return "Wicket Toolkit Core initializer";
	}
}