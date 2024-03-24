package seedu.address.model;

import java.util.ArrayList;

/**
 * Extended version of Address Book storing History of Address Book.
 */
public class VersionedAddressBook extends AddressBook {
    private ArrayList<ReadOnlyAddressBook> addressBookStateList;
    private int currentStatePointer;

    /**
     * Creates VersionedAddressBook and initialize addressBookStateList to track history.
     * @param addressBook initial address book
     */
    public VersionedAddressBook(ReadOnlyAddressBook addressBook) {
        super(addressBook);

        addressBookStateList = new ArrayList<>();
        addressBookStateList.add(new AddressBook(addressBook));
        currentStatePointer = 0;
    }

    /**
     * Commits the new state of address book into tracker addressBookStateList.
     */
    public void commit() {
        updateSubAddressBookStateList();
        this.addressBookStateList.add(new AddressBook(this));
        currentStatePointer += 1;
    }

    /**
     * Reverts the address book to its previous state.
     */
    public void undo() {
        currentStatePointer -= 1;
        resetData(addressBookStateList.get(currentStatePointer));
    }

    /**
     * Checks whether the address book can be reverted to a previous state.
     * @return true if there is a previous state to revert to, else false.
     */
    public boolean canUndo() {
        if (currentStatePointer <= 0) {
            // cannot undo, no more address book behind the list.
            return false;
        }
        return true;
    }

    /**
     * Advances the address book to its next state if possible.
     */
    public void redo() {
        currentStatePointer += 1;
        resetData(addressBookStateList.get(currentStatePointer));
    }

    /**
     * Checks whether the address book can be advanced to a next state.
     * @return true if here is a next state to advance to, else false.
     */
    public boolean canRedo() {
        if (currentStatePointer >= addressBookStateList.size() - 1) {
            // cannot redo, no more address book in front of list.
            return false;
        }
        return true;
    }

    /**
     * Updates the sublist of address book states up to the current state pointer.
     */
    private void updateSubAddressBookStateList() {
        ArrayList<ReadOnlyAddressBook> copy = new ArrayList<>();

        for (int i = 0; i <= currentStatePointer; i++) {
            copy.add(addressBookStateList.get(i));
        }

        this.addressBookStateList = copy;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof VersionedAddressBook)) {
            return false;
        }

        VersionedAddressBook otherAddressBook = (VersionedAddressBook) other;
        return super.equals(otherAddressBook)
                && addressBookStateList.equals(otherAddressBook.addressBookStateList)
                && currentStatePointer == otherAddressBook.currentStatePointer;
    }

}
