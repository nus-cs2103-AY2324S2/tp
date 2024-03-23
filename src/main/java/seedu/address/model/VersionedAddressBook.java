package seedu.address.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class VersionedAddressBook extends AddressBook {
    private ArrayList<ReadOnlyAddressBook> addressBookStateList ;
    int currentStatePointer;

    public VersionedAddressBook(ReadOnlyAddressBook addressBook) {
        super(addressBook);

        addressBookStateList = new ArrayList<>();
        addressBookStateList.add(new AddressBook(addressBook));
        currentStatePointer = 0;
    }

    public void commit() {
        updateSubAddressBookStateList();
        this.addressBookStateList.add(new AddressBook(this));
        currentStatePointer += 1;
    }

    public void undo() {
        currentStatePointer -= 1;
        resetData(addressBookStateList.get(currentStatePointer));
    }

    public boolean canUndo() {
        if (currentStatePointer <= 0) {
            // cannot undo, no more address book behind the list.
            return false;
        }
        return true;
    }

    public void redo() {
        currentStatePointer += 1;
        resetData(addressBookStateList.get(currentStatePointer));
    }

    public boolean canRedo() {
        if (currentStatePointer >= addressBookStateList.size() - 1) {
            // cannot redo, no more address book in front of list.
            return false;
        }
        return true;
    }

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
