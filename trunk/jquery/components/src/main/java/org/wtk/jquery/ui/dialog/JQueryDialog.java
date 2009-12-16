package org.wtk.jquery.ui.dialog;

import net.sf.json.JSONObject;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.wtk.api.Titled;
import org.wtk.behavior.ajax.EventlessAjaxBehavior;
import org.wtk.component.panel.base.BasePanel;
import org.wtk.component.support.plugin.PluginManager;
import org.wtk.jquery.model.JQuery;
import org.wtk.jquery.model.JQueryOption;
import org.wtk.jquery.ui.util.JQueryOptionSerializer;

import java.io.Serializable;

import static java.lang.String.format;
import static org.wtk.component.list.ComponentListView.ITEM_ID;
import static org.wtk.jquery.model.JQuery.Position;


/**
 * jQuery UI Dialog.
 * <p/>
 * For demo and options details see <a href="http://jqueryui.com/demos/dialog/">jQuery UI site</a>.
 *
 * @author Yoav Aharoni
 */
public class JQueryDialog<T extends Serializable> extends BasePanel<T> implements Titled {
	private static JQueryOptionSerializer<JQueryDialog> optionSerializer = new JQueryOptionSerializer<JQueryDialog>(JQueryDialog.class);
	private EventlessAjaxBehavior closeEvent;
	private Integer height;
	private Integer maxHeight;
	private Integer maxWidth;
	private Integer minHeight = 150;
	private Integer minWidth = 150;
	private Integer width = 300;
	private JQuery.Effect hideEffect;
	private JQuery.Effect showEffect;
	private JQueryDialog wrappingDialog;
	private Position position = Position.CENTER_CENTER;
	private String closeText;
	private String cssClass;
	private String title;

	private boolean bgiFrame = false;
	private boolean draggable = true;
	private boolean modal = true;
	private boolean resizable = true;
	private int zIndex = 1000;

	public JQueryDialog() {
		this(null);
	}

	public JQueryDialog(IModel model) {
		super(ITEM_ID, model);

		setOutputMarkupPlaceholderTag(true);
		closeEvent = new AjaxCloseEvent();
		add(closeEvent);
	}

	public BasePanel wrap(Form form) {
		wrappingDialog = new JQueryDialogFormWrapper(this, form);
		return this;
	}

	public void show() {
		getPlugin().show(this);
	}

	public void close() {
		getPlugin().close(this);
	}

	@JQueryOption()
	public Integer getHeight() {
		return height;
	}

	public JQueryDialog<T> setHeight(Integer height) {
		this.height = height;
		return this;
	}

	@JQueryOption()
	public Integer getMaxHeight() {
		return maxHeight;
	}

	public JQueryDialog<T> setMaxHeight(Integer maxHeight) {
		this.maxHeight = maxHeight;
		return this;
	}

	@JQueryOption()
	public Integer getMaxWidth() {
		return maxWidth;
	}

	public JQueryDialog<T> setMaxWidth(Integer maxWidth) {
		this.maxWidth = maxWidth;
		return this;
	}

	@JQueryOption()
	public Integer getMinHeight() {
		return minHeight;
	}

	public JQueryDialog<T> setMinHeight(Integer minHeight) {
		this.minHeight = minHeight;
		return this;
	}

	@JQueryOption()
	public Integer getMinWidth() {
		return minWidth;
	}

	public JQueryDialog<T> setMinWidth(Integer minWidth) {
		this.minWidth = minWidth;
		return this;
	}

	@JQueryOption()
	public Integer getWidth() {
		return width;
	}

	public JQueryDialog<T> setWidth(Integer width) {
		this.width = width;
		return this;
	}

	@JQueryOption()
	public Position getPosition() {
		return position;
	}

	public JQueryDialog<T> setPosition(Position position) {
		this.position = position;
		return this;
	}

	@JQueryOption()
	public String getButtons() {
		return null;
	}

	@JQueryOption()
	public String getCloseText() {
		if (closeText == null) {
			closeText = getSafeString("jquery.dialog.close");
		}
		return closeText;
	}

	public JQueryDialog<T> setCloseText(String closeText) {
		this.closeText = closeText;
		return this;
	}

	@JQueryOption()
	public boolean isAutoOpen() {
		return true;
	}

	@JQueryOption()
	public boolean isCloseOnEscape() {
		return true;
	}

	@JQueryOption()
	public boolean isDraggable() {
		return draggable;
	}

	public JQueryDialog<T> setDraggable(boolean draggable) {
		this.draggable = draggable;
		return this;
	}

	@JQueryOption()
	public boolean isModal() {
		return modal;
	}

	public JQueryDialog<T> setModal(boolean modal) {
		this.modal = modal;
		return this;
	}

	@JQueryOption()
	public boolean isResizable() {
		return resizable;
	}

	public JQueryDialog<T> setResizable(boolean resizable) {
		this.resizable = resizable;
		return this;
	}

	@JQueryOption()
	public boolean isStack() {
		return true;
	}

	@JQueryOption(name = "bgiframe")
	public boolean isBgiFrame() {
		return bgiFrame;
	}

	public JQueryDialog<T> setBgiFrame(boolean bgiFrame) {
		this.bgiFrame = bgiFrame;
		return this;
	}

	@JQueryOption(name = "close")
	public final String getInternalCloseScript() {
		return closeEvent.getCallFunction();
	}

	@JQueryOption(name = "dialogClass")
	public String getCssClass() {
		return cssClass;
	}

	public JQueryDialog<T> setCssClass(String cssClass) {
		this.cssClass = cssClass;
		return this;
	}

	@JQueryOption(name = "hide")
	public JQuery.Effect getHideEffect() {
		return hideEffect;
	}

	public JQueryDialog<T> setHideEffect(JQuery.Effect hideEffect) {
		this.hideEffect = hideEffect;
		return this;
	}

	@JQueryOption(name = "show")
	public JQuery.Effect getShowEffect() {
		return showEffect;
	}

	public JQueryDialog<T> setShowEffect(JQuery.Effect showEffect) {
		this.showEffect = showEffect;
		return this;
	}

	@JQueryOption(name = "zIndex")
	public int getZIndex() {
		return zIndex;
	}

	public JQueryDialog<T> setZIndex(int zIndex) {
		this.zIndex = zIndex;
		return this;
	}

	@Override
	@JQueryOption()
	public String getTitle() {
		if (title == null) {
			title = getSafeString("jquery.dialog.title");
		}
		return title;
	}

	public JQueryDialog<T> setTitle(String title) {
		this.title = title;
		return this;
	}

	JQueryDialog getWrappingDialog() {
		return wrappingDialog;
	}

	private JQueryDialogPlugin getPlugin() {
		return PluginManager.get().getPlugin(JQueryDialogPlugin.class);
	}

	protected String getShowScript() {
		return format("jQuery('#%s').wtk('dialog','show',%s);", getMarkupId(), getOptionsJson());
	}

	protected String getCloseScript() {
		return format("jQuery('#%s').wtk('dialog','close');", getMarkupId());
	}

	protected String getDestroyScript() {
		return format("jQuery('#%s').wtk('dialog','destroy');", getMarkupId());
	}

	protected void onCloseButtonClicked(AjaxRequestTarget target) {
	}

	protected void onShow(AjaxRequestTarget target) {
	}

	protected void onClose(AjaxRequestTarget target) {
	}

	protected JSONObject getOptionsJson() {
		return optionSerializer.toJSON(this);
	}

	protected IAjaxCallDecorator getAjaxCallDecorator() {
		return null;
	}

	private class AjaxCloseEvent extends EventlessAjaxBehavior {
		@Override
		protected void onEvent(AjaxRequestTarget target) {
			getPlugin().handleCloseButtonClick(JQueryDialog.this, target);
		}

		@Override
		protected IAjaxCallDecorator getAjaxCallDecorator() {
			return JQueryDialog.this.getAjaxCallDecorator();
		}
	}
}