package seedu.address.testutil;

import seedu.address.model.StaffConnect;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code StaffConnect ab = new StaffConnectBuilder().withPerson("John", "Doe").build();}
 */
public class StaffConnectBuilder {

    private StaffConnect staffConnect;

    public StaffConnectBuilder() {
        staffConnect = new StaffConnect();
    }

    public StaffConnectBuilder(StaffConnect staffConnect) {
        this.staffConnect = staffConnect;
    }

    /**
     * Adds a new {@code Person} to the {@code StaffConnect} that we are building.
     */
    public StaffConnectBuilder withPerson(Person person) {
        staffConnect.addPerson(person);
        return this;
    }

    public StaffConnect build() {
        return staffConnect;
    }
}
