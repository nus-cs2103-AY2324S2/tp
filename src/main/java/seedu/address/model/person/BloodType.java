package seedu.address.model.person;

import java.util.Objects;

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
    public BloodType(Type type, Rh rh) {
        this.type = type;
        this.rh = rh;
    }

    public String getType() {
        return type.toString();
    }

    public String getRh() {
        return rh.toString();
    }

    @Override
    public String toString() {
        return this.type.toString() + this.rh.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, rh);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof BloodType)) {
            return false;
        }
        BloodType otherBloodType = (BloodType) other;
        return type.equals(otherBloodType.type) && rh.equals(otherBloodType.rh);
    }
}
