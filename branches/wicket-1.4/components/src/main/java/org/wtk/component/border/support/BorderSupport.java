package org.wtk.component.border.support;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.resolver.IComponentResolver;
import org.wtk.component.panel.base.BasePanel;

import java.util.Iterator;

/**
 * @author Yoav Aharoni
 */
public class BorderSupport extends MarkupContainer implements IBorderSupport<BorderSupport, BasePanel>, IComponentResolver {
	private int markupIndex;

	public BorderSupport(String id) {
		super(id);
		setRenderBodyOnly(true);
	}

	/**
	 * Replace wrapping BasePanel border.
	 * <p/>
	 * BasePanel's borders implement decorator design pattern, allowing to add border on-top-of border.
	 * <p/>
	 * NOTE: Make sure wrapping borderPanel markup contains &lt;wicket:body/&gt; tag,
	 * as this panel will render as its body.
	 *
	 * @param borderPanel border panel
	 * @return this (to allow method call chaining).
	 * @see BorderSupport#getBorder()
	 * @see BorderSupport#removeBorder()
	 * @see BorderSupport#wrap(BasePanel)
	 * @see BorderSupport#wrapOuterBorder(BasePanel)
	 */
	@Override
	public BorderSupport replaceBorder(BasePanel borderPanel) {
		removeAll();
		if (borderPanel != null) {
			add(borderPanel);
			borderPanel.setRenderBodyOnly(true);
		}
		return this;
	}

	/**
	 * Removes wrapping BasePanel border.
	 *
	 * @return this (to allow method call chaining).
	 * @see BorderSupport#replaceBorder(BasePanel)
	 */
	@Override
	public BorderSupport removeBorder() {
		return replaceBorder(null);
	}

	/**
	 * Wraps panel with given borderPanel while keeping borders hierarchy.
	 *
	 * @param borderPanel border panel
	 * @return this (to allow method call chaining).
	 * @see BorderSupport#replaceBorder(BasePanel)
	 */
	@Override
	public BorderSupport wrap(BasePanel borderPanel) {
		if (borderPanel == null) {
			// ignore null
			return this;
		}
		BasePanel previousBorder = getBorder();
		replaceBorder(borderPanel);
		borderPanel.wrap(previousBorder);
		return this;
	}

	/**
	 * Wraps outermost border panel with given borderPanel.
	 *
	 * @param borderPanel border panel
	 * @return this (to allow method call chaining).
	 * @see BorderSupport#replaceBorder(BasePanel)
	 */
	@Override
	public BorderSupport wrapOuterBorder(BasePanel borderPanel) {
		if (borderPanel == null) {
			// ignore null
			return this;
		}

		IBorderSupport<BorderSupport, BasePanel> current = this;
		IBorderSupport next;
		while ((next = current.getBorder()) != null) {
			current = next;
		}
		current.replaceBorder(borderPanel);
		return this;
	}

	@Override
	public boolean resolve(final MarkupContainer container, final MarkupStream markupStream, final ComponentTag tag) {
		MarkupContainer current = container.getParent();
		MarkupContainer parent = getParent();
		while (current != null) {
			if (current.equals(this)) {
				Component component = parent.get(tag.getId());
				if (component != null) {
					renderComponent(markupStream);
					return true;
				}
			}
			current = current.getParent();
		}

		if ("child".equals(tag.getName()) && tag.isOpenClose()) {
			if (parent != null) {
				MarkupContainer grandParent = parent.getParent();
				if (grandParent instanceof BorderSupport) {
					markupStream.next();
					BorderSupport parentBorder = (BorderSupport) grandParent;
					parentBorder.renderInnerPanel();
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @return Border wrapping this panel.
	 */
	@Override
	public BasePanel getBorder() {
		Iterator iterator = iterator();
		if (iterator.hasNext()) {
			Object child = iterator.next();
			if (child instanceof BasePanel) {
				return (BasePanel) child;
			}
		}
		return null;
	}

	@Override
	public boolean isTransparentResolver() {
		return true;
	}

	@Override
	protected void onRender(MarkupStream markupStream) {
		markupIndex = markupStream.getCurrentIndex();
		BasePanel border = getBorder();
		if (border != null) {
			border.markRendering(true);
			border.renderAssociatedMarkup("panel",
					"Markup for a BasePanel component has to contain part '<wicket:extend>'");
			border.rendered();
		} else {
			renderComponent(markupStream);
		}
	}

	private void renderInnerPanel() {
		MarkupStream markupStream = getMarkupStream();
		markupStream.setCurrentIndex(markupIndex);
		renderComponent(markupStream);
		while (markupStream.hasMore()) {
			markupStream.next();
		}
	}
}
