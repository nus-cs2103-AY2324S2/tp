package vitalConnect.model.person.identificationInformation;


/**
 * Represents a Person's identification information
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class IdentificationInformation {
    // Identity fields
    private final Name name;
    private final Nric nric;

    /**
     * Constructs a {@code IdentificationInformation}.
     *
     * @param name A valid name.
     */
    public IdentificationInformation(Name name) {
        this.name = name;
        this.nric = new Nric(); // TODO after adding Nric class
    }

    /**
     * TODO
     */
    public static boolean isValidIdentificationInformation(String nameTest, String nric) {
        return nameTest.matches(Name.VALIDATION_REGEX) && nric.matches(Nric.VALIDATION_REGEX);
    }

    public Name getName() {
        return name;
    }

    public Nric getNric() {
        return nric;
    }

    @Override
    public String toString() {
        return name + " " + nric;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof IdentificationInformation)) {
            return false;
        }

        IdentificationInformation otherInfo = (IdentificationInformation) other;
        return name.equals(otherInfo.name) && nric.equals(otherInfo.nric);
    }

    @Override
    public int hashCode() {
        return name.hashCode() + nric.hashCode();
    }
}
