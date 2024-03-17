package seedu.address.model.filename;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Filename containing contacts to be added to the contact manager.
 * Guarantees: immutable; filename is valid as declared in {@link #isValidFilename(String)}
 */
public class Filename {
    public static final String MESSAGE_CONSTRAINTS = "Filenames should be alphanumeric.";

    public static final String MESSAGE_DUPLICATE = "Filenames should be unique.";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";
    public final String filename;

    /**
     * Constructs a {@code Filename}.
     *
     * @param filename A valid filename.
     */
    public Filename(String filename) {
        requireNonNull(filename);
        this.filename = filename;
    }

    /**
     * Returns true if a given String is a valid filename.
     *
     * @param name String to be checked.
     * @return A boolean.k
     */
    public static boolean isValidFilename(String name) {
        return name.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Filename)) {
            return false;
        }

        Filename otherFilename = (Filename) other;
        return this.filename.equals(otherFilename.filename);
    }

    /**
     * Returns a hashcode representing the Filename object.
     *
     * @return An integer representing the hashcode.
     */
    @Override
    public int hashCode() {
        return this.filename.hashCode();
    }

    /**
     * Formats the Filename as a String for viewing.
     *
     * @return A formatted String.
     */
    @Override
    public String toString() {
        return this.filename;
    }
}
