package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.startup.Startup;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the startups list.
     * This list will not contain any duplicate startups.
     */
    ObservableList<Startup> getStartupList();

}
