package seedu.address.testutil;

import seedu.address.model.PayBack;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building PayBack objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class PayBackBuilder {

    private PayBack payBack;

    public PayBackBuilder() {
        payBack = new PayBack();
    }

    public PayBackBuilder(PayBack payBack) {
        this.payBack = payBack;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public PayBackBuilder withPerson(Person person) {
        payBack.addPerson(person);
        return this;
    }

    public PayBack build() {
        return payBack;
    }
}
