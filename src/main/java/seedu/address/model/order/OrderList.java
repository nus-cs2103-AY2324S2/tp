package seedu.address.model.order;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.exceptions.OrderNotFoundException;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * Represents the list of active orders in the addressbook.
 */
public class OrderList implements Iterable<Order> {
    /**
     * Keeps track of the order ids, ensuring that all ids are unique.
     */
    private int orderIdCounter;
    /**
     * The hashmap with mappings from Orders to their OrderID
     */
    private HashMap<Integer, Order> orderList;
    /**
     * The Lists which stores the Order Objects.
     */
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
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Order toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameOrder);
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
     * Replaces the order {@code target} in the list with {@code editedOrder}.
     * {@code target} must exist in the list.
     */
    public void setOrder(Order target, Order editedOrder) {
        requireAllNonNull(target, editedOrder);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        internalList.set(index, editedOrder);
    }

    /**
     * Deletes an order from the order list.
     * @param toDelete The order id of the order that is to be deleted.
     */
    public void deleteOrder(int toDelete) {
        requireNonNull(toDelete);
        if (toDelete < 1) {
            throw new NullPointerException();
        }
        Order oldOrder = orderList.get(toDelete);
        if (oldOrder == null) {
            throw new OrderNotFoundException();
        }
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
        if (orderId < 1) {
            throw new NullPointerException();
        }
        Order currOrder = orderList.get(orderId);
        if (currOrder == null) {
            throw new OrderNotFoundException();
        }
        if (!currOrder.isSameOrder(toEdit)) {
            Order oldOrder = orderList.get(orderId);
            int oldOrderIndex = internalList.indexOf(oldOrder);
            orderList.put(orderId, toEdit);
            internalList.set(oldOrderIndex, toEdit);
        }
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

    /**
     * Gives the number of orders in the {@code OrderList}
     *
     * @return number of orders
     */
    public int size() {
        return orderList.size();
    }

    /**
     * Gets the order corresponding to the index
     *
     * @param i the order index
     * @return the order corresponding to the index
     */
    public Order getOrder(int i) {
        if (i < 1) {
            throw new NullPointerException();
        }

        Order order = orderList.get(i);

        if (order == null) {
            throw new OrderNotFoundException();
        }

        return order;
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     * @return the backing list as an unmodifiable {@code ObservableList}.
     */
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

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof OrderList)) {
            return false;
        }

        OrderList otherOrderList = (OrderList) other;
        return internalList.equals(otherOrderList.internalList);
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
