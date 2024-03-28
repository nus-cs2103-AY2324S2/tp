package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's unique id in the address book, smallest id is 1.
 * Guarantees: immutable; is valid as declared in {@link #isValidId(int)}
 */
public class Id {

    public static final String MESSAGE_CONSTRAINTS = "Ids should only be any integer value larger than 0";

    private static int nextId = 1;

    public final int value;

    /**
     * Constructs an {@code Id} containing the next available id.
     */
    private Id() {
        value = nextId++;
    }

    /**
     * Constructs an {@code Id} with the given id.
     */
    private Id(int id) {
        value = id;
    }

    /**
     * Factory method to generate an {@code Id} with the next available id.
     * @return A new {@code Id} instance containing the next available id.
     */
    public static Id generateNextId() {
        return new Id();
    }

    /**
     * Factory method to generate an {@code Id} with the given id, and updates the next available id.
     * @return A new {@code Id} instance containing the given id.
     */
    public static Id generateId(int id) {
        checkArgument(isValidId(id), MESSAGE_CONSTRAINTS);
        if (id >= nextId) {
            nextId = id + 1;
        }
        return new Id(id);
    }

    /**
     * Factory method to generate an {@code Id} with the given id. Does not affect the next available id.
     * @return A new {@code Id} instance containing the given id.
     */
    public static Id generateTempId(int id) {
        checkArgument(isValidId(id), MESSAGE_CONSTRAINTS);
        return new Id(id);
    }

    /**
     * Returns true if a given int is a valid id.
     */
    public static boolean isValidId(int test) {
        return test > 0;
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
