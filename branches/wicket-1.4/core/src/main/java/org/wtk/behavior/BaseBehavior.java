package org.wtk.behavior;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.IBehavior;
import org.apache.wicket.markup.ComponentTag;

/**
 * @author Yoav Aharoni
 */
public class BaseBehavior implements IBehavior {
	/**
	 * @see org.apache.wicket.behavior.IBehavior#beforeRender(org.apache.wicket.Component)
	 */
	public void beforeRender(Component component) {
	}

	/**
	 * @see org.apache.wicket.behavior.IBehavior#bind(org.apache.wicket.Component)
	 */
	public void bind(final Component component) {
	}

	/**
	 * @see org.apache.wicket.behavior.IBehavior#detach(Component)
	 */
	public void detach(Component component) {

	}

	/**
	 * @see org.apache.wicket.behavior.IBehavior#exception(org.apache.wicket.Component,
	 *      java.lang.RuntimeException)
	 */
	public void exception(Component component, RuntimeException exception) {
	}

	/**
	 * @see org.apache.wicket.behavior.IBehavior#getStatelessHint(org.apache.wicket.Component)
	 */
	public boolean getStatelessHint(Component component) {
		return true;
	}

	/**
	 * @see org.apache.wicket.behavior.IBehavior#onComponentTag(org.apache.wicket.Component,
	 *      org.apache.wicket.markup.ComponentTag)
	 */
	public void onComponentTag(final Component component, final ComponentTag tag) {
	}


	/**
	 * @see org.apache.wicket.behavior.IBehavior#afterRender(org.apache.wicket.Component)
	 */
	public void afterRender(final Component component) {
	}

	/**
	 * @see org.apache.wicket.behavior.IBehavior#isEnabled(Component)
	 */
	public boolean isEnabled(Component component) {
		return true;
	}

	/**
	 * @see org.apache.wicket.behavior.IBehavior#isTemporary()
	 */
	public boolean isTemporary() {
		return false;
	}
}
