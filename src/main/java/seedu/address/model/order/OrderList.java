package seedu.address.model.order;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;

/**
 * Represents the list of active orders in the addressbook.
 */
public class OrderList {
    /**
     * Keeps track of the order ids, ensuring that all ids are unique.
     */
    private int orderIdCounter;
    /**
     * The order list of all active orders.
     */
    private HashMap<Integer, Order> orderList;

    /**
     * OrderList class constructor.
     */
    public OrderList() {
        orderList = new HashMap<>();
        orderIdCounter = 1;
    }

    /**
     * Getter method for orderIdCounter.
     * @return The current orderId to be used.
     */
    public int getOrderIdCounter() {
        return orderIdCounter;
    }

    /**
     * Adds an order to the order list.
     * @param orderId The order id of the order to be added.
     * @param toAdd The order that is to be added.
     * @param person The person that the order is tagged to.
     */
    public void addOrder(int orderId, Order toAdd, Person person) {
        requireAllNonNull(orderId, toAdd);
        orderList.put(orderId, toAdd);
        person.addOrder(toAdd);
        orderIdCounter++;
    }

    /**
     * Deletes an order from the order list.
     * @param toDelete The order id of the order that is to be deleted.
     */
    public void deleteOrder(int toDelete) {
        orderList.remove(toDelete);
    }

    /**
     * Edits an order from the order list.
     * @param orderId The order id of the order that is to be edited.
     * @param toEdit The new order to replace the old order.
     */
    public void editOrder(int orderId, Order toEdit) {
        requireAllNonNull(orderId, toEdit);
        // Check if order is same, wait for isSameOrder method
        orderList.put(orderId, toEdit);
    }

    /**
     * Converts the active order list to be displayed on the GUI.
     * @return The active order list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Order> getOrderList() {
        ObservableList<Order> ls = FXCollections.observableArrayList();
        for (int orderId : orderList.keySet()) {
            ls.add(orderList.get(orderId));
        }
        return ls;
    }
}
