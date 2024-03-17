package seedu.address.model.person;

import java.util.Objects;

/**
 * Represents a Person's diagnosis in the address book.
 * Guarantees: immutable;
 */
public class Diagnosis {
    private final String diagnosis;

    public Diagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    @Override
    public String toString() {
        return this.diagnosis;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Diagnosis)) {
            return false;
        }

        Diagnosis otherDiagnosis = (Diagnosis) other;
        return diagnosis.equals(otherDiagnosis.diagnosis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(diagnosis);
    }
}
