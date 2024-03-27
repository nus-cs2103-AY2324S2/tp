package seedu.address.logic.commands.sortfunctions;

import seedu.address.model.AddressBook;

/**
 * Sorting strategy to sort address book by name in lexicographically order
 */
public class SortByName implements SortStrategy {
    @Override
    public void sort(AddressBook addressBook) {
        addressBook.getPersons().sortListByName();
    }

    @Override
    public String getCategory() {
        return "name";
    }
}
