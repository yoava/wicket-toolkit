package org.wtk.component.panel.base;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.resolver.IComponentResolver;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.StringResourceModel;
import org.wtk.component.support.border.BorderSupport;
import org.wtk.component.support.border.IBorderSupport;

import java.io.Serializable;

/**
 * Extremely useful combination of panel and border.
 * <p/>
 * A BasePanel can be used as a panel or as a border:<br/>
 * - To use it as a panel simply extend it and add an associated html file with &lt;wicket:extend&gt;<br/>
 * - To use it as a panel simply extend it and add an associated html file with &lt;wicket:extend&gt; and &lt;wicket:body&gt;<br/>
 * - Either way you can also &lt;wicket:child&gt; to allow further markup inheritance.
 * <p/>
 * Each BasePanel can also be wrapped with any number of other BasePanels serving as borders simply by calling wrapPanel or wrapOuterBorder.<br/>
 * This allows creating highly flexible panels, without adding extra markup for borders.
 * <p/>
 * Generics are also supported for model object.
 * <p/>
 *
 * @author Yoav Aharoni
 * @see Panel
 * @see BasePanel#BasePanel()
 * @see BasePanel#wrap(BasePanel)
 * @see BasePanel#wrapOuterBorder(BasePanel)
 * @see BasePanel#replaceBorder(BasePanel)
 * @see BasePanel#removeBorder()
 */
@SuppressWarnings({"TypeMayBeWeakened", "JavaDoc"})
public class BasePanel<T extends Serializable> extends Panel implements IBorderSupport<BasePanel, BasePanel>, IComponentResolver {
	private static final String BORDER_ID = "wtk-internal-border";

	/**
	 * Creates a new BasePanel which acts as border.
	 *
	 * @see Panel#Panel(String)
	 */
	public BasePanel() {
		this(BORDER_ID);
		setRenderBodyOnly(true);
	}

	public BasePanel(IModel model) {
		this(BORDER_ID, model);
		setRenderBodyOnly(true);
	}

	/**
	 * @see Panel#Panel(String)
	 */
	public BasePanel(String id) {
		this(id, (IModel) null);
	}

	/**
	 * @see Panel#Panel(String,IModel)
	 */
	public BasePanel(String id, T modelObject) {
		this(id, new CompoundPropertyModel(modelObject));
	}

	/**
	 * @see Panel#Panel(String,IModel)
	 */
	public BasePanel(String id, IModel model) {
		super(id, model);

		add(new BorderSupport(BORDER_ID));
	}

	@Override
	protected void onComponentTag(ComponentTag tag) {
		super.onComponentTag(tag);
	}


	public IModel resourceModel(String key) {
		return resourceModel(key, null);
	}

	@Override
	public boolean resolve(MarkupContainer container, MarkupStream markupStream, ComponentTag tag) {
		return false;
	}

	public IModel resourceModel(String key, String defaultValue) {
		return new PanelResourceModel(key, defaultValue);
	}

	@SuppressWarnings({"unchecked"})
	public T getPanelModelObject() {
		return (T) getModelObject();
	}

	public void setPanelModelObject(T object) {
		setModelObject(object);
	}

	@Override
	public BasePanel getBorder() {
		return getBorderSupport().getBorder();
	}

	@Override
	public BasePanel replaceBorder(BasePanel borderPanel) {
		getBorderSupport().replaceBorder(borderPanel);
		return this;
	}

	@Override
	public BasePanel removeBorder() {
		getBorderSupport().removeBorder();
		return this;
	}

	@Override
	public BasePanel wrap(BasePanel borderPanel) {
		getBorderSupport().wrap(borderPanel);
		return this;
	}

	@Override
	public BasePanel wrapOuterBorder(BasePanel borderPanel) {
		getBorderSupport().wrapOuterBorder(borderPanel);
		return this;
	}

	public IModel bindProperty(String expression) {
		return new PanelPropertyModel(expression);
	}

	@SuppressWarnings({"unchecked"})
	private IBorderSupport<IBorderSupport, BasePanel> getBorderSupport() {
		return (IBorderSupport<IBorderSupport, BasePanel>) get(BORDER_ID);
	}

	public class PanelResourceModel extends StringResourceModel {
		public PanelResourceModel(String key) {
			this(key, "");
		}

		public PanelResourceModel(String key, String defaultValue) {
			super(key, BasePanel.this, getModel(), defaultValue);
		}

		@Override
		public String toString() {
			final Object value = getObject();
			return value == null ? null : value.toString();
		}
	}

	public class PanelPropertyModel extends PropertyModel {
		public PanelPropertyModel(String expression) {
			super(BasePanel.this, expression);
		}

		@Override
		public String toString() {
			final Object value = getObject();
			return value == null ? null : value.toString();
		}
	}
}
