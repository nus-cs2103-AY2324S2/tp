package seedu.address.model.person;

/**
 * Represents a Person's blood type in the address book.
 * Guarantees: immutable;
 */
public class BloodType {
    private enum Type { A, B, AB, O };
    private enum Rh { POSITIVE, NEGATIVE };
    private final Type type;
    private final Rh rh;

    /**
     * Constructs a {@code BloodType}.
     *
     * @param type A valid blood type.
     * @param rh A valid Rh factor.
     */
    public BloodType(String type, String rh) {
        this.type = Type.valueOf(type);
        this.rh = Rh.valueOf(rh);
    }
}
