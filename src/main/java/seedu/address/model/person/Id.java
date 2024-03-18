package seedu.address.model.person;

/**
 * Represents a Person's unique id in the address book, smallest id is 1.
 * Guarantees: immutable; is always valid; each instance has
 * a unique value; only at most one instance for each unique value.
 */
public class Id {

    public static final String MESSAGE_CONSTRAINTS =
            "Ids should only be any integer value larger than 0";

    private static int nextId = 1;

    public final int value;

    /**
     * Constructs an {@code Id} containing the next available number.
     */
    private Id() {
        value = nextId++;
    }

    /**
     * Constructs an {@code Id} with the given id, and sets the next available id.
     */
    public Id(int id) {
        value = id;
        if (id >= nextId) {
            nextId = id + 1;
        }
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
