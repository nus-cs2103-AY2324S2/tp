package seedu.address.model.person;

/**
 * Represents a Person's rating in the address book.
 */
public class Rating {
    private int rating; // Rating out of 5 stars

    public Rating() {
        this.rating = 0; // Initialize with 0 stars
    }

    public void setRating(int rating) {
        if (rating >= 0 && rating <= 5) {
            this.rating = rating;
        } else {
            System.out.println("Invalid rating. Please provide a rating between 0 and 5.");
        }
    }

    public int getRating() {
        return rating;
    }
}
