package seedu.realodex.testutil;

import seedu.realodex.model.Realodex;
import seedu.realodex.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code Realodex ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private Realodex realodex;

    public AddressBookBuilder() {
        realodex = new Realodex();
    }

    public AddressBookBuilder(Realodex realodex) {
        this.realodex = realodex;
    }

    /**
     * Adds a new {@code Person} to the {@code Realodex} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        realodex.addPerson(person);
        return this;
    }

    public Realodex build() {
        return realodex;
    }
}
