package seedu.address.model.coursemate;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a CourseMate's rating in the contact list.
 * Guarantees: immutable; is valid as declared in {@link #isValidRating(String)}
 */
public class Rating {
    public static final String MESSAGE_CONSTRAINTS = "Ratings are given by a (single digit) number between 1 to 5.\n"
            + "The default is 0 if a coursemate is not yet rated.";
    public static final String VALIDATION_REGEX = "\\b[0-5]\\b";
    public final String value;
    public final String ratingText;

    /**
     * Constructs a {@code Rating}.
     *
     * @param rating A valid rating.
     */
    public Rating(String rating) {
        requireNonNull(rating);
        checkArgument(isValidRating(rating), MESSAGE_CONSTRAINTS);
        this.value = rating;
        this.ratingText = toStringWithStars(rating);
    }

    /**
     * Returns true if a given string is a valid rating.
     */
    public static boolean isValidRating(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns a rating text shown by number of stars according to the rating.
     * If rating is 0, empty text is returned.
     * @param rating string of integer between 1 and 5.
     */
    public static String toStringWithStars(String rating) {
        requireNonNull(rating);
        checkArgument(isValidRating(rating), MESSAGE_CONSTRAINTS);

        int ratingValue;
        try {
            ratingValue = Integer.parseInt(rating);
        } catch (NumberFormatException e) {
            // when rating is a non-integer string (though this would not happen)
            ratingValue = 0;
        }

        if (0 < ratingValue && ratingValue < 6) {
            StringBuilder ratingText = new StringBuilder("");
            for (int i = 0; i < 5; i++) {
                if (i < ratingValue) {
                    // Black star (U+2605 in Unicode)
                    ratingText.append("★");
                } else {
                    // White Star (U+2606 in Unicode)
                    ratingText.append("☆");
                }
            }
            return ratingText.toString();
        } else {
            return "";
        }
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
        if (!(other instanceof Rating)) {
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
