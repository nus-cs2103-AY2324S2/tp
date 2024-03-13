package vitalconnect.testutil;

import vitalconnect.model.Clinic;
import vitalconnect.model.person.Person;

/**
 * A utility class to help with building Clinic objects.
 * Example usage: <br>
 *     {@code Clinic ab = new ClinicBuilder().withPerson("John", "Doe").build();}
 */
public class ClinicBuilder {

    private Clinic clinic;

    public ClinicBuilder() {
        clinic = new Clinic();
    }

    public ClinicBuilder(Clinic clinic) {
        this.clinic = clinic;
    }

    /**
     * Adds a new {@code Person} to the {@code Clinic} that we are building.
     */
    public ClinicBuilder withPerson(Person person) {
        clinic.addPerson(person);
        return this;
    }

    public Clinic build() {
        return clinic;
    }
}
