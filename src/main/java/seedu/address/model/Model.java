package seedu.address.model;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.order.Order;
import seedu.address.model.order.Product;
import seedu.address.model.order.Quantity;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Order> PREDICATE_SHOW_ALL_ORDERS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Replaces the given order {@code target} with {@code editedPerson}.
     * {@code target} must exist in the order list.
     */
    void setOrder(Order target, Order editedOrder);

    /**
     * Adds the given order {@code newOrder} to the given person {@code person} into the orderlist.
     * @param newOrder Order object to be added to the orderlist.
     * @param person Person object to be attached to the order, which will be added to the orderlist.
     */
    void addOrder(Order newOrder, Person person);

    /**
     * Deletes the given order.
     * The order must exist in the address book.
     */
    void deleteOrder(int id);

    /**
     * Sets the quantity of the product in the order.
     * If the product is not in the order yet, add the product and set its quantity.
     * @param currProduct Product of which quantity to be editted.
     * @param newQuantity new Quantity of the specified product.
     */
    Order editOrder(Order target, Product currProduct, Quantity newQuantity);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Finds a person based on their phone number.
     *
     * @param phoneNumber The phone number to search for.
     * @return An Optional containing the found Person, or an empty Optional if no person with the phone number exists.
     */
    Optional<Person> findPersonByPhoneNumber(String phoneNumber);

    Order findOrderByIndex(int id);
    /**
     * Returns an unmodifiable view of the filtered person list.
     *
     * @return an unmodifiable view of the filtered person list.
     */
    ObservableList<Order> getFilteredOrderList();
    /**
     * Updates the filter of the filtered order list to filter by the given {@code predicate}.
     *
     * @param predicate predicate to update the filtered order list with.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredOrderList(Predicate<Order> predicate);

    /**
     * Returns the number of orders in the order list.
     *
     * @return the number of orders in the order list
     */
    int getOrderListSize();

    void clearOrderFilter();
}
