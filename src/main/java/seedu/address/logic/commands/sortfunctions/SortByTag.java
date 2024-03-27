package seedu.address.logic.commands.sortfunctions;

import seedu.address.model.AddressBook;

/**
 * Sorting strategy to sort address book by tag in lexicographically order
 */
public class SortByTag implements SortStrategy {
    @Override
    public void sort(AddressBook addressBook) {
        addressBook.getPersons().sortListByTag();
    }

    @Override
    public String getCategory() {
        return "tag";
    }
}
