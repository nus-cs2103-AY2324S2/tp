package seedu.address.model.person;

/**
 * Represents a Person's Note in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidRating(String)}
 */
public class Rating {
    public static final String MESSAGE_CONSTRAINTS = "Rating must be an integer between 1 and 5 inclusive.";

    /*
     * Ratings must be integers between 1 and 5 inclusive. A 0 rating is unrated
     */
    public static final String VALIDATION_REGEX = "[0-5]";

    private String value;

    /**
     * Constructs an {@code Rating}.
     *
     * @param rating A valid rating.
     */
    public Rating(String rating) {
        value = rating;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidRating(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public void setValue(String value) {
        this.value = value;
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
        if (!(other instanceof Note)) {
            return false;
        }

        Rating otherRating = (Rating) other;
        return value.equals(otherRating.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
