package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderList;
import seedu.address.model.order.Product;
import seedu.address.model.order.Quantity;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final OrderList orders;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        orders = new OrderList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    public void setOrders(List<Order> orders) {
        this.orders.setOrders(orders);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setOrders(newData.getOrderList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Adds an {@code Order} to the {@code OrderList} of this address book.
     *
     */
    public void addOrder(Order order) {
        int orderCounter = orders.getOrderIdCounter();
        order.setID(orderCounter);
        orders.addOrder(order);
    }

    /**
     * Adds an {@code Order} to the {@code OrderList} of this addressbook with a predefined ID (for storage purposes).
     * @param order Order Object to be added into the Order List.
     */
    public void addOrderWithID(Order order) {
        int orderId = order.getId();
        orders.addOrderWithID(order, orderId);
    }

    public Order findOrderByIndex(int id) {
        return orders.getOrder(id);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    public void setOrder(Order target, Order edittedOrder) {
        requireNonNull(edittedOrder);

        orders.setOrder(target, edittedOrder);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public Order editOrder(Order target, Product currProduct, Quantity newQuantity) {
        requireNonNull(target);
        return target.updateOrder(currProduct, newQuantity);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    /**
     * Removes {@code Order} from the {@code OrderList} of this {@code AddressBook}.
     *
     * @param id index of order to remove
     */
    public void removeOrder(int id) {
        orders.deleteOrder(id);
    }


    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", persons)
                .toString();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    //Make sure to implement abstract method for this
    public ObservableList<Order> getOrderList() {
        return orders.asUnmodifiableObservableList();
    }
    public OrderList getOrderListClass() {
        return orders;
    }

    public int getOrderListSize() {
        return orders.size();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;
        return persons.equals(otherAddressBook.persons) && orders.equals(otherAddressBook.orders);
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
