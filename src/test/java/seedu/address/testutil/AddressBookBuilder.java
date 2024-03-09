package seedu.address.testutil;

import seedu.address.model.StaffConnect;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code StaffConnect ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private StaffConnect staffConnect;

    public AddressBookBuilder() {
        staffConnect = new StaffConnect();
    }

    public AddressBookBuilder(StaffConnect staffConnect) {
        this.staffConnect = staffConnect;
    }

    /**
     * Adds a new {@code Person} to the {@code StaffConnect} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        staffConnect.addPerson(person);
        return this;
    }

    public StaffConnect build() {
        return staffConnect;
    }
}
