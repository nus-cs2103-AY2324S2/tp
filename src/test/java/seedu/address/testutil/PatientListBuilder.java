package seedu.address.testutil;

import seedu.address.model.PatientList;
import seedu.address.model.patient.Patient;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code PatientList ab = new PatientListBuilder().withPerson("John", "Doe").build();}
 */
public class PatientListBuilder {

    private PatientList patientList;

    public PatientListBuilder() {
        patientList = new PatientList();
    }

    public PatientListBuilder(PatientList patientList) {
        this.patientList = patientList;
    }

    /**
     * Adds a new {@code Person} to the {@code PatientList} that we are building.
     */
    public PatientListBuilder withPerson(Patient patient) {
        patientList.addPerson(patient);
        return this;
    }

    public PatientList build() {
        return patientList;
    }
}
