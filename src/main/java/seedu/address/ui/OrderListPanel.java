package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.order.Order;

/**
 * Panel containing list of orders.
 */
public class OrderListPanel extends UiPart<Region> {
    private static final String FXML = "OrderListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(OrderListPanel.class);

    @javafx.fxml.FXML
    private ListView<Order> orderListView;

    /**
     * Creates a {@code OrderListPanel} with the given {@code ObservableList}.
     */
    public OrderListPanel(ObservableList<Order> orderList) {
        super(FXML);
        orderListView.setItems(orderList);
        orderListView.setCellFactory(listView -> new OrderListPanel.OrderListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of an {@code Order} using an {@code OrderCard}.
     */
    class OrderListViewCell extends ListCell<Order> {
        @Override
        protected void updateItem(Order order, boolean empty) {
            super.updateItem(order, empty);

            if (empty || order == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new OrderCard(order).getRoot());
            }
        }
    }

    /**
     * Updates the order list view with a given list of orders.
     * This method sets the items of the order list view to the specified list of orders,
     * effectively updating the UI to display these orders. It can be used to apply filters
     * or to reset the order list view to its original state.
     *
     * @param orders The {@link ObservableList} of {@link Order} objects to be displayed.
     *               This list replaces the current items in the order list view.
     */
    public void updateDisplayedOrders(ObservableList<Order> orders) {
        orderListView.setItems(orders);
    }

}
