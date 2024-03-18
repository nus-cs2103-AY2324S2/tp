package seedu.address.model.person;

/**
 * Represents a Person's unique id in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidId(int)}
 */
public class Id {

    private static int nextId = 1;
    public final int value;

    /**
     * Constructs an {@code Id} containing the next available number.
     */
    private Id() {
        value = nextId++;
    }

    /**
     * Factory method to generate an {@code Id} with the next available number.
     * @return A new {@code Id} instance containing the next available number.
     */
    public static Id generateId() {
        return new Id();
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Id)) {
            return false;
        }

        Id otherId = (Id) other;
        return value == otherId.value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
