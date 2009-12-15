package org.wtk.component.list;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
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
		final RepeatingView itemsContainer = getItemsContainer();
		final MarkupContainer container = new WebMarkupContainer(itemsContainer.newChildId());
		itemsContainer.add(container);
		container.add(item);
	}

	public void removeItem(T item) {
		internalRemoveItem(item);
		if (shouldRefresh()) {
			Ajax.appendJavascript("wtk('#%s').remove();", item.getMarkupId());
		}
	}

	@SuppressWarnings({"unchecked"})
	public void internalRemoveItem(T item) {
		final Iterator<T> iterator = getItemsIterator();
		while (iterator.hasNext()) {
			final T current = iterator.next();
			if (current.equals(item)) {
				current.getParent().remove();
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

	public Iterator<T> getItemsIterator() {
		return new ItemsIterator();
	}

	private RepeatingView getItemsContainer() {
		return (RepeatingView) get("items");
	}

	private class ItemsIterator implements Iterator<T> {
		private Iterator containerIterator = getItemsContainer().iterator();

		@SuppressWarnings({"unchecked"})
		@Override
		public T next() {
			final MarkupContainer container = (MarkupContainer) containerIterator.next();
			return (T) container.get(ITEM_ID);
		}

		@Override
		public void remove() {
			containerIterator.remove();
		}

		@Override
		public boolean hasNext() {
			return containerIterator.hasNext();
		}
	}
}
