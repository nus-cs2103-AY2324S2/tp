package seedu.address.logic.commands.sortfunctions;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;

/**
 * Interface representing sorting strategy for the address book
 * Different implementations of this interface can be used to sort based on different criteria
 */
public interface SortStrategy {
    /**
     * Sorts address book according to the specific condition
     * @param addressBook Address book containing all people added
     * @throws CommandException If an error occurs while sorting
     */
    void sort(AddressBook addressBook) throws CommandException;

    /**
     * Gets the category associated with this sorting strategy
     * @return The category for sorting
     */
    String getCategory();
}
