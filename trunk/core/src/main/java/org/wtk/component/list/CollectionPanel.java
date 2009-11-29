package org.wtk.component.list;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author Yoav Aharoni
 */
public class CollectionPanel<T extends Component> extends Panel {
    public static String ITEM_ID = "item";

    public CollectionPanel(String id, Collection<T> collection) {
        this(id, new Model((Serializable) collection));
    }

    public CollectionPanel(String id, IModel listModel) {
        super(id, listModel);
        setOutputMarkupId(true);

        add(new ListView("items", getModel()) {
            @Override
            protected void populateItem(ListItem item) {
                item.add((Component) item.getModelObject());
            }
        });
    }

    @SuppressWarnings({"unchecked"})
    public Collection<T> getItems() {
        return (Collection<T>) getModelObject();
    }

    public void addItem(T item) {
        getItems().add(item);
        refresh();
    }

    public void removeItem(T item) {
        getItems().remove(item);
        refresh();
    }

    public void refresh() {
        AjaxRequestTarget target = AjaxRequestTarget.get();
        if (target != null) {
            Page page = findPage();
            if (page != null && page.equals(RequestCycle.get().getResponsePage())) {
                target.addComponent(this);
            }
        }
    }
}
