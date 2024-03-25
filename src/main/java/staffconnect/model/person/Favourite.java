package staffconnect.model.person;

import static java.util.Objects.requireNonNull;
import static staffconnect.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's favourite status in the staff book.
 */
public class Favourite {
    public static final String MESSAGE_CONSTRAINTS = "String should only be either \"Favourite\" or \"Not favourite\"";
    private static final String FAVOURITE_SYMBOL = "\u2605";
    private static final String NOT_FAVOURITE_SYMBOL = "";
    private static final String FAVOURITE = "Favourite";
    private static final String NOT_FAVOURITE = "Not favourite";
    private boolean isFavourite;

    /**
     * Constructs a {@code Favourite} with a boolean parameter.
     *
     * @param isFavourite A boolean indicating whether a Person is a favourite contact.
     */
    public Favourite(boolean isFavourite) {
        requireNonNull(isFavourite);
        this.isFavourite = isFavourite;
    }

    /**
     * Constructs a {@code Favourite} with a String parameter.
     *
     * @param favouriteStatus A valid string representing a favourite status.
     */
    public Favourite(String favouriteStatus) {
        requireNonNull(favouriteStatus);
        checkArgument(isValidFavourite(favouriteStatus), MESSAGE_CONSTRAINTS);
        this.isFavourite = favouriteStatus.equals(FAVOURITE);
    }

    /**
     * Returns true if a given string is a valid favourite status.
     */
    public static boolean isValidFavourite(String test) {
        return test.equals(FAVOURITE) || test.equals(NOT_FAVOURITE);
    }

    public boolean hasFavourite() {
        return isFavourite;
    }

    /**
     * Returns unicode character representing a Person's favourite status shown in the GUI.
     */
    public String toDisplayString() {
        return isFavourite ? FAVOURITE_SYMBOL : NOT_FAVOURITE_SYMBOL;
    }
    @Override
    public String toString() {
        return isFavourite ? FAVOURITE : NOT_FAVOURITE;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Favourite)) {
            return false;
        }

        Favourite otherFavourite = (Favourite) other;
        return isFavourite == otherFavourite.isFavourite;
    }

    @Override
    public int hashCode() {
        return Boolean.hashCode(isFavourite);
    }
}
