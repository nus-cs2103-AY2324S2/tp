package seedu.address.model.person;

/**
 * Represents a Person's rating in the address book.
 */
public class Rating {
    private int rating; // Rating out of 5 stars

    public Rating() {
        this.rating = 0; // Initialize with 0 stars
    }

    /**
     * Constructs an {@code Rating}.
     *
     * @param rating A valid rating.
     */
    public Rating(int rating) {
        if (rating >= 0 && rating <= 5) {
            this.rating = rating;
        } else {
            throw new IllegalArgumentException("Invalid rating. Please provide a rating between 0 and 5.");
        }
    }

    public void setRating(int rating) {
        if (rating >= 0 && rating <= 5) {
            this.rating = rating;
        } else {
            throw new IllegalArgumentException("Invalid rating. Please provide a rating between 0 and 5.");
        }
    }

    /**
     * Returns true if a given integer is a valid rating.
     */
    public static boolean isValidRating(int test) {
        return test >= 0 && test <= 5;
    }


    public int getRating() {
        return rating;
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
        return rating == otherRating.rating;
    }
}
