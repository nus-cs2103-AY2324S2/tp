package vitalconnect.model.person.identificationinformation;


/**
 * Represents a Person's identification information
 * Guarantees: immutable; is valid as declared in {@link #isValidIdentificationInformation(String, String)}
 */
public class IdentificationInformation {
    // Identity fields
    private final Name name;
    private final Nric nric;

    /**
     * Constructs a {@code IdentificationInformation}.
     *
     * @param name A valid name.
     * @param nric A valid nric.
     */
    public IdentificationInformation(Name name, Nric nric) {
        this.name = name;
        this.nric = nric;
    }

    /**
     * Constructs a {@code IdentificationInformation}.
     *
     * @param name A valid name.
     * @param nric A valid nric.
     */
    public IdentificationInformation(String name, String nric) {
        this.name = new Name(name);
        this.nric = new Nric(nric);
    }

    /**
     * Returns true if a given info is a valid IdentificationInformation.
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
        return "[name=" + name + ", nric=" + nric + "]";
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
