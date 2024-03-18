package seedu.address.model;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

public class VersionedAddressBook extends AddressBook {
    private HashMap<Integer, AddressBook> addressBookStateList;
    private int currentStatePointer;
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    public VersionedAddressBook() {
        addressBookStateList = new HashMap<>();
        currentStatePointer = 0;
    }

    public void commitInitial(AddressBook initialState) {
        addressBookStateList.put(currentStatePointer, new AddressBook(initialState));
    }

    public void commit(AddressBook currState) {
        currentStatePointer++;
        addressBookStateList.put(currentStatePointer, new AddressBook(currState));
    }

    public AddressBook undo() {
        currentStatePointer--;
        return new AddressBook(addressBookStateList.get(currentStatePointer));
    }

    public boolean canUndo() {
        return currentStatePointer != 0;
    }

}
