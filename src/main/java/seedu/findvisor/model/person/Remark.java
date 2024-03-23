package seedu.findvisor.model.person;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

/**
 * Represents the remark about a Person in the address book.
 * Guarantees: immutable; any value is valid.
 */
public class Remark {

    public final String value;

    /**
     * Constructs a {@code Remark}.
     *
     * @param remark A valid remark.
     */
    public Remark(String remark) {
        requireNonNull(remark);
        value = remark;
    }

    /**
     * Creates an {@code Optional<Remark>} of given remark if remark is not blank.
     * Returns {@code Optional.empty()} if given remark is blank otherwise.
     *
     * @param remark value of remark.
     */
    public static Optional<Remark> createRemark(String remark) {
        requireNonNull(remark);
        if (remark.isBlank()) {
            return Optional.empty();
        }
        return Optional.of(new Remark(remark));
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Remark)) {
            return false;
        }

        Remark otherRemark = (Remark) other;
        return value.equals(otherRemark.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
