package seedu.address.model.order;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;

/**
 * Represents the list of active orders in the addressbook.
 */
public class OrderList implements Iterable<Order> {
    /**
     * Keeps track of the order ids, ensuring that all ids are unique.
     */
    private int orderIdCounter;
    /**
     * The order list of all active orders.
     */
    private HashMap<Integer, Order> orderList;
    private final ObservableList<Order> internalList = FXCollections.observableArrayList();
    private final ObservableList<Order> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

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
     * @param toAdd The order that is to be added.
     * @param person The person that the order is tagged to.
     */
    public void addOrder(Order toAdd, Person person) {
        requireAllNonNull(toAdd);
        //make sure that our addordercommand has a means to set the OrderID
        orderList.put(toAdd.getId(), toAdd);
        toAdd.setID(orderIdCounter);
        internalList.add(toAdd);
        person.addOrder(toAdd);
        orderIdCounter++;
    }

    /**
     * Deletes an order from the order list.
     * @param toDelete The order id of the order that is to be deleted.
     */
    public void deleteOrder(int toDelete) {
        Order oldOrder = orderList.get(toDelete);
        Integer oldOrderIndex = internalList.indexOf(oldOrder);
        orderList.remove(toDelete);
        internalList.remove(oldOrderIndex);
    }

    /**
     * Edits an order from the order list.
     * @param orderId The order id of the order that is to be edited.
     * @param toEdit The new order to replace the old order.
     */
    public void editOrder(int orderId, Order toEdit) {
        requireAllNonNull(orderId, toEdit);
        Order oldOrder = orderList.get(orderId);
        int oldOrderIndex = internalList.indexOf(oldOrder);
        // Check if order is same, wait for isSameOrder method
        orderList.put(orderId, toEdit);
        internalList.set(oldOrderIndex, toEdit);
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

    public ObservableList<Order> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Order> iterator() {
        return internalList.iterator();
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean ordersAreUnique(List<Order> orders) {
        for (int i = 0; i < orders.size() - 1; i++) {
            for (int j = i + 1; j < orders.size(); j++) {
                if (orders.get(i).isSameOrder(orders.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
