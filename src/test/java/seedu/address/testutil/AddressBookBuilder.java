package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.startup.Startup;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withStartup("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private AddressBook addressBook;

    public AddressBookBuilder() {
        addressBook = new AddressBook();
    }

    public AddressBookBuilder(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Startup} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withStartup(Startup startup) {
        addressBook.addStartup(startup);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
