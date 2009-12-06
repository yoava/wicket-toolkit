package org.wtk.component.list;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.wtk.resource.WicketToolkitHeadResource;
import org.wtk.util.Ajax;

import java.util.Iterator;

/**
 * @author Yoav Aharoni
 */
public class ItemsContainer<T extends Component> extends Panel {
	public static final String ITEM_ID = "item";

	public ItemsContainer(String id) {
		super(id);
		setOutputMarkupId(true);
		add(new WicketToolkitHeadResource());
		add(new RepeatingView("items"));
	}

	public void addItem(T item) {
		internalAddItem(item);
		if (shouldRefresh()) {
			Ajax.prependJavascript("wtk('#%s').append('<span id=\"%s\"></span>');", getMarkupId(), item.getMarkupId());
			Ajax.render(item);
		}
	}

	public void internalAddItem(T item) {
		item.setOutputMarkupId(true);
		if (!item.getId().equals(ITEM_ID)) {
			throw new IllegalArgumentException(String.format("Item must have id=\"%s\" (id is \"%s\"). Please use ItemsContainer.ITEM_ID", ITEM_ID, item.getId()));
		}
		getItemsContainer().add(new ItemContainer(item));
	}

	public void removeItem(T item) {
		internalRemoveItem(item);
		if (shouldRefresh()) {
			Ajax.appendJavascript("wtk('#%s').remove();", item.getMarkupId());
		}
	}

	public Iterator<T> getItemsIterator() {
		return new ItemsIterator();
	}

	@SuppressWarnings({"unchecked"})
	public void internalRemoveItem(T item) {
		final Iterator iterator = getItemsContainer().iterator();
		while (iterator.hasNext()) {
			final ItemContainer container = (ItemContainer) iterator.next();
			if (container.getItem().equals(item)) {
				container.remove();
				return;
			}
		}
	}

	public boolean shouldRefresh() {
		AjaxRequestTarget target = AjaxRequestTarget.get();
		if (target != null) {
			Page page = findPage();
			if (page != null && page.equals(RequestCycle.get().getResponsePage())) {
				return true;
			}
		}
		return false;
	}

	private RepeatingView getItemsContainer() {
		return (RepeatingView) get("items");
	}

	private class ItemContainer extends WebMarkupContainer {
		private ItemContainer(T item) {
			super(getItemsContainer().newChildId());
			add(item);
			setRenderBodyOnly(true);
		}

		@SuppressWarnings({"unchecked"})
		public T getItem() {
			return (T) get(ITEM_ID);
		}
	}

	private class ItemsIterator implements Iterator<T> {
		private Iterator containerIterator = getItemsContainer().iterator();

		@Override
		public boolean hasNext() {
			return containerIterator.hasNext();
		}

		@SuppressWarnings({"unchecked"})
		@Override
		public T next() {
			final ItemContainer container = (ItemContainer) containerIterator.next();
			return container.getItem();
		}

		@Override
		public void remove() {
			containerIterator.remove();
		}
	}
}
