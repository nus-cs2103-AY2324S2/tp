package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.startup.Startup;
import seedu.address.model.startup.UniqueStartupList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameStartup comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueStartupList startups;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        startups = new UniqueStartupList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Startups in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the startup list with {@code startups}.
     * {@code startups} must not contain duplicate startups.
     */
    public void setStartups(List<Startup> startups) {
        this.startups.setStartups(startups);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setStartups(newData.getStartupList());
    }

    //// startup-level operations

    /**
     * Returns true if a startup with the same identity as {@code startup} exists in the address book.
     */
    public boolean hasStartup(Startup startup) {
        requireNonNull(startup);
        return startups.contains(startup);
    }

    /**
     * Adds a startup to the address book.
     * The startup must not already exist in the address book.
     */
    public void addStartup(Startup p) {
        startups.add(p);
    }

    /**
     * Replaces the given startup {@code target} in the list with {@code editedStartup}.
     * {@code target} must exist in the address book.
     * The startup identity of {@code editedStartup} must not be the same as another existing startup
     * in the address book.
     */
    public void setStartup(Startup target, Startup editedStartup) {
        requireNonNull(editedStartup);

        startups.setStartup(target, editedStartup);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeStartup(Startup key) {
        startups.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("startups", startups)
                .toString();
    }

    @Override
    public ObservableList<Startup> getStartupList() {
        return startups.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;
        return startups.equals(otherAddressBook.startups);
    }

    @Override
    public int hashCode() {
        return startups.hashCode();
    }
}
