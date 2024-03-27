package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.Client;
import seedu.address.model.person.Housekeeper;
import seedu.address.model.person.Person;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    //ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the clients list.
     * This list will not contain any duplicate clients.
     */
    ObservableList<Client> getClientList();

    /**
     * Returns an unmodifiable view of the housekeepers list.
     * This list will not contain any duplicate housekeepers.
     */
    ObservableList<Housekeeper> getHousekeeperList();

}
