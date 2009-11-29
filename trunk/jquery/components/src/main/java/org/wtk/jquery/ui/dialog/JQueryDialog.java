package org.wtk.jquery.ui.dialog;

import net.sf.json.JSONObject;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.value.ValueMap;
import org.wtk.api.Titled;
import org.wtk.component.panel.base.BasePanel;
import org.wtk.component.support.plugin.PluginManager;
import org.wtk.jquery.model.JQuery;

import java.io.Serializable;

import static org.wtk.component.list.CollectionPanel.ITEM_ID;


/**
 * jQuery UI Dialog.
 * <p/>
 * For demo and options details see <a href="http://jqueryui.com/demos/dialog/">jQuery UI site</a>.
 *
 * @author Yoav Aharoni
 */
public class JQueryDialog<T extends Serializable> extends BasePanel<T> implements Titled {
	private ValueMap options;

	public JQueryDialog() {
		super(ITEM_ID);
	}

	public JQueryDialog(IModel model) {
		super(ITEM_ID, model);
	}

	{
		setOutputMarkupPlaceholderTag(true);
		initOptions();
	}

	private void initOptions() {
		options = new ValueMap();
		options.put(Option.AUTO_OPEN.getKey(), true);
		options.put("bgiframe", false);
		options.put("buttons", null);
		options.put(Option.CLOSE_ON_ESCAPE.getKey(), true);
		options.put("closeText", null);
		options.put("dialogClass", null);
		options.put(Option.DRAGGABLE.getKey(), true);
		options.put("hide", null);
		options.put("height", null);
		options.put("maxHeight", null);
		options.put("maxWidth", null);
		options.put("minHeight", 150);
		options.put("minWidth", 150);
		options.put(Option.MODAL.getKey(), false);
		options.put("pocsition", "center");
		options.put(Option.RESIZABLE.getKey(), true);
		options.put(Option.STACK.getKey(), true);
		options.put("title", null);
		options.put("width", 400);
		options.put("zIndex", 1000);
		options.put("show", null);
		options.put("close", "alert(1)");
	}

	public void show() {
		getPlugin().show(this);
	}

	public void close() {
		getPlugin().close(this);
	}

	public String getShowScript() {
		return String.format("jQuery('#%s').dialog(%s);", getMarkupId(), getOptionsJson());
	}

	public String getCloseScript() {
		return String.format("jQuery('#%s').dialog('close');", getMarkupId());
	}

	protected void onShow(AjaxRequestTarget target) {
	}

	protected void onClose(AjaxRequestTarget target) {
	}

	private JQueryDialogPlugin getPlugin() {
		return PluginManager.get().getPlugin(JQueryDialogPlugin.class);
	}

	private JSONObject getOptionsJson() {
		options.put("closeText", getCloseText());
		options.put("title", getTitle());
		return JSONObject.fromObject(options);
	}

	public String getCloseText() {
		return getString("jquery.dialog.close");
	}

	@Override
	public String getTitle() {
		final String title = options.getString("title");
		if (title == null) {
			return getString("jquery.dialog.title");
		}
		return title;
	}

	public JQueryDialog<T> setTitle(String title) {
		options.put("title", title);
		return this;
	}

	public String getDialogCssClass() {
		return options.getString("dialogClass");
	}

	public JQueryDialog<T> setDialogCssClass(String cssClass) {
		options.put("dialogClass", cssClass);
		return this;
	}

	public String getHideEffect() {
		return options.getString("hide");
	}

	public JQueryDialog<T> setHideEffect(JQuery.Effect effect) {
		options.put("hide", effect.getEffect());
		return this;
	}

	public String getShowEffect() {
		return options.getString("show");
	}

	public JQueryDialog<T> setShowEffect(JQuery.Effect effect) {
		options.put("show", effect.getEffect());
		return this;
	}

	public int getWidth() {
		return options.getInt("width");
	}

	public JQueryDialog<T> setWidth(int width) {
		options.put("width", width);
		return this;
	}

	public int getHeight() {
		return options.getInt("height");
	}

	public JQueryDialog<T> setHeight(int height) {
		options.put("height", height);
		return this;
	}

	public int getMaxWidth() {
		return options.getInt("maxWidth");
	}

	public JQueryDialog<T> setMaxWidth(int maxWidth) {
		options.put("maxWidth", maxWidth);
		return this;
	}

	public int getMaxHeight() {
		return options.getInt("maxHeight");
	}

	public JQueryDialog<T> setMaxHeight(int maxHeight) {
		options.put("maxHeight", maxHeight);
		return this;
	}

	public int getMinWidth() {
		return options.getInt("minWidth");
	}

	public JQueryDialog<T> setMinWidth(int minWidth) {
		options.put("minWidth", minWidth);
		return this;
	}

	public int getMinHeight() {
		return options.getInt("minHeight");
	}

	public JQueryDialog<T> setMinHeight(int minHeight) {
		options.put("minHeight", minHeight);
		return this;
	}

	public int getZIndex() {
		return options.getInt("zIndex");
	}

	public JQueryDialog<T> setZIndex(int zIndex) {
		options.put("zIndex", zIndex);
		return this;
	}

	public JQuery.Position[] getPosition() {
		return (JQuery.Position[]) options.get("position");
	}

	public JQueryDialog<T> setPosition(JQuery.Position... position) {
		options.put("position", position);
		return this;
	}

	public JQueryDialog<T> setTheme(JQuery.Theme theme) {
		add(JQuery.createScopedTheme(theme));
		return setDialogCssClass(theme.getTheme());
	}

	public boolean isBgiFrame() {
		return getOption(Option.BGI_FRAME);
	}

	public JQueryDialog<T> setBgiFrame(boolean enabled) {
		return setOption(Option.BGI_FRAME, enabled);
	}

	public boolean isCloseOnEscape() {
		return getOption(Option.CLOSE_ON_ESCAPE);
	}

	public JQueryDialog<T> setCloseOnEscape(boolean enabled) {
		return setOption(Option.CLOSE_ON_ESCAPE, enabled);
	}

	public boolean isDraggable() {
		return getOption(Option.DRAGGABLE);
	}

	public JQueryDialog<T> setDraggable(boolean enabled) {
		return setOption(Option.DRAGGABLE, enabled);
	}

	public boolean isModal() {
		return getOption(Option.MODAL);
	}

	public JQueryDialog<T> setModal(boolean enabled) {
		return setOption(Option.MODAL, enabled);
	}

	public boolean isResizable() {
		return getOption(Option.RESIZABLE);
	}

	public JQueryDialog<T> setResizable(boolean enabled) {
		return setOption(Option.RESIZABLE, enabled);
	}

	public boolean isStacked() {
		return getOption(Option.STACK);
	}

	public JQueryDialog<T> setStacked(boolean enabled) {
		return setOption(Option.STACK, enabled);
	}

	private boolean getOption(Option option) {
		return options.getBoolean(option.getKey());
	}

	private JQueryDialog<T> setOption(Option option, boolean enabled) {
		options.put(option.getKey(), enabled);
		return this;
	}

	private enum Option {
		AUTO_OPEN("autoOpen"), BGI_FRAME("bgiframe"), CLOSE_ON_ESCAPE("closeOnEscape"), DRAGGABLE("draggable"), MODAL("modal"), RESIZABLE("resizable"), STACK("stack");

		private String key;

		Option(String key) {
			this.key = key;
		}

		private String getKey() {
			return key;
		}
	}
}
