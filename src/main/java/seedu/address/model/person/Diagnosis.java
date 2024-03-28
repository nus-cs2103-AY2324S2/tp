package seedu.address.model.person;


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

    /**
     * Returns given placeholder string if value field is not initialised
     * @param alt
     * @return placeholder string
     */
    public String orElse(String alt) {
        return diagnosis == null ? alt : diagnosis;
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
        return diagnosis.hashCode();
    }
}
