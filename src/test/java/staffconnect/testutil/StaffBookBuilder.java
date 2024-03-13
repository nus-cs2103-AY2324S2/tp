package staffconnect.testutil;

import staffconnect.model.StaffBook;
import staffconnect.model.person.Person;

/**
 * A utility class to help with building StaffBook objects.
 * Example usage: <br>
 *     {@code StaffBook ab = new StaffBookBuilder().withPerson("John", "Doe").build();}
 */
public class StaffBookBuilder {

    private StaffBook staffBook;

    public StaffBookBuilder() {
        staffBook = new StaffBook();
    }

    public StaffBookBuilder(StaffBook staffBook) {
        this.staffBook = staffBook;
    }

    /**
     * Adds a new {@code Person} to the {@code StaffBook} that we are building.
     */
    public StaffBookBuilder withPerson(Person person) {
        staffBook.addPerson(person);
        return this;
    }

    public StaffBook build() {
        return staffBook;
    }
}
