package seedu.address.model;

import java.util.HashMap;

/**
 * A VersionedAddressBook to store Address Book states
 */
public class VersionedAddressBook extends AddressBook {
    private HashMap<Integer, AddressBook> addressBookStateList;
    private int currentStatePointer;

    /**
     * Creates a VersionedAddressBook
     */
    public VersionedAddressBook() {
        addressBookStateList = new HashMap<>();
        currentStatePointer = 0;
    }

    /**
     * Commits the provided initial Address Book state to the addressBookStateList
     *
     * @param initialState the initial state of the Address Book
     */
    public void commitInitial(AddressBook initialState) {
        addressBookStateList.put(currentStatePointer, new AddressBook(initialState));
    }

    /**
     * Commits the provided current Address Book state to the addressBookStateList
     *
     * @param currState the current state of the Address Book
     */
    public void commit(AddressBook currState) {
        currentStatePointer++;
        addressBookStateList.put(currentStatePointer, new AddressBook(currState));
    }

    /**
     * Restores the previous state of the Address Book
     *
     * @return the previous state of the Address Book
     */
    public AddressBook undo() {
        currentStatePointer--;
        return new AddressBook(addressBookStateList.get(currentStatePointer));
    }

    /**
     * Determines if there are previous states to restore
     *
     * @return whether there are previous states to restore
     */
    public boolean canUndo() {
        return currentStatePointer != 0;
    }

}
