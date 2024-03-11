package seedu.address.testutil;

import java.util.HashSet;

import seedu.address.model.person.*;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PatientBuilder {

    public static final String DEFAULT_NRIC = "S1234567A";
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_DOB = "2002-01-30";
    public static final String DEFAULT_PHONE = "85355255";

    private NRIC nric;
    private Name name;
    private DoB dob;
    private Phone phone;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PatientBuilder() {
        nric = new NRIC(DEFAULT_NRIC);
        name = new Name(DEFAULT_NAME);
        dob = new DoB(DEFAULT_DOB);
        phone = new Phone(DEFAULT_PHONE);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PatientBuilder(Person patientToCopy) {
        if (patientToCopy.getType() != Type.PATIENT) {
            throw new RuntimeException();
        }
        nric = patientToCopy.getNRIC();
        name = patientToCopy.getName();
        dob = patientToCopy.getDoB();
        phone = patientToCopy.getPhone();
    }

    public PatientBuilder withNRIC(String nric) {
        this.nric = new NRIC(nric);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PatientBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    public PatientBuilder withDoB(String dob) {
        this.dob = new DoB(dob);
        return this;
    }

    public PatientBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    public Patient build() {
        return new Patient(nric, name, dob, phone);
    }

}
