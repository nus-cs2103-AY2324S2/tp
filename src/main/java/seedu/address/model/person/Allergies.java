package seedu.address.model.person;


/**
 * Represents a Person's allergies in the address book.
 * Guarantees: immutable;
 */
public class Allergies {
    private final String allergies;

    public Allergies(String allergies) {
        this.allergies = allergies;
    }

    public String getAllergies() {
        return allergies;
    }

    /**
     * Returns given placeholder string if value field is not initialised
     * @param alt
     * @return placeholder string
     */
    public String orElse(String alt) {
        return allergies == null ? alt : allergies;
    }

    @Override
    public String toString() {
        return this.allergies;
    }

    @Override
    public int hashCode() {
        return allergies.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Allergies)) {
            return false;
        }

        Allergies otherAllergies = (Allergies) other;
        return allergies.equals(otherAllergies.allergies);
    }
}
