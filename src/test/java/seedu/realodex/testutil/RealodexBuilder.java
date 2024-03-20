package seedu.realodex.testutil;

import seedu.realodex.model.Realodex;
import seedu.realodex.model.person.Person;

/**
 * A utility class to help with building Realodex objects.
 * Example usage: <br>
 *     {@code Realodex ab = new RealodexBuilder().withPerson("John", "Doe").build();}
 */
public class RealodexBuilder {

    private Realodex realodex;

    public RealodexBuilder() {
        realodex = new Realodex();
    }

    public RealodexBuilder(Realodex realodex) {
        this.realodex = realodex;
    }

    /**
     * Adds a new {@code Person} to the {@code Realodex} that we are building.
     */
    public RealodexBuilder withPerson(Person person) {
        realodex.addPerson(person);
        return this;
    }

    public Realodex build() {
        return realodex;
    }
}
