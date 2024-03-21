package seedu.address.model;

import java.util.ArrayList;
import java.util.HashMap;

import seedu.address.logic.commands.CommandResult;

/**
 * A VersionedAddressBook to store Address Book states
 */
public class VersionedAddressBook extends AddressBook {
    private ArrayList<AddressBook> addressBookStateList;
    private int currentStatePointer;
    private HashMap<Integer, CommandResult> commandResultList;

    /**
     * Creates a VersionedAddressBook
     */
    public VersionedAddressBook() {
        addressBookStateList = new ArrayList<>();
        currentStatePointer = 0;
        commandResultList = new HashMap<>();
    }

    /**
     * Commits the provided initial Address Book state to the addressBookStateList
     *
     * @param initialState the initial state of the Address Book
     */
    public void commitInitial(AddressBook initialState) {
        addressBookStateList.add(currentStatePointer, new AddressBook(initialState));
    }

    /**
     * Commits the provided current Address Book state to the addressBookStateList
     *
     * @param currState the current state of the Address Book
     */
    public void commit(AddressBook currState, CommandResult commandResult) {
        currentStatePointer++;
        addressBookStateList.add(currentStatePointer, new AddressBook(currState));
        commandResultList.put(currentStatePointer, commandResult);
    }

    /**
     * Determines if there are previous states to restore
     *
     * @return whether there are previous states to restore
     */
    public boolean canUndo() {
        return currentStatePointer != 0;
    }

    /**
     * Undoes the previous command that changed data
     *
     * @return the previous state of the Address Book
     */
    public AddressBook undo() {
        currentStatePointer--;
        return new AddressBook(addressBookStateList.get(currentStatePointer));
    }

    /**
     * Determines if there are future states to restore
     *
     * @return whether there are future states to restore
     */
    public boolean canRedo() {
        return currentStatePointer != (addressBookStateList.size() - 1);
    }

    /**
     * Redoes the previously undone command
     *
     * @return the state of the Address Book before the previously undone command
     */
    public AddressBook redo() {
        currentStatePointer++;
        return new AddressBook(addressBookStateList.get(currentStatePointer));
    }

    /**
     * Determines if there are redundant future states present that should be purged
     *
     * @return whether there are redundant future states
     */
    public boolean shouldPurge() {
        return currentStatePointer != (addressBookStateList.size() - 1);
    }

    /**
     * Purges redundant future states
     */
    public void purge() {
        while (addressBookStateList.size() > (currentStatePointer + 1)) {
            addressBookStateList.remove(addressBookStateList.size() - 1);
            commandResultList.remove(commandResultList.size() - 1);
        }
    }

    public CommandResult getUndoneCommand() {
        return commandResultList.get(currentStatePointer + 1);
    }

    public CommandResult getRedoneCommand() {
        return commandResultList.get(currentStatePointer);
    }
}
