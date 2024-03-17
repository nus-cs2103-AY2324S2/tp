package seedu.address.model.person;

import java.util.Objects;

/**
 * Represents a Person's symptom in the address book.
 * Guarantees: immutable;
 */
public class Symptom {
    private final String symptom;

    public Symptom(String symptom) {
        this.symptom = symptom;
    }

    public String getSymptom() {
        return symptom;
    }

    @Override
    public String toString() {
        return this.symptom;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Symptom)) {
            return false;
        }

        Symptom otherSymptom = (Symptom) other;
        return symptom.equals(otherSymptom.symptom);
    }
    @Override
    public int hashCode() {
        return symptom.hashCode();
    }
}
