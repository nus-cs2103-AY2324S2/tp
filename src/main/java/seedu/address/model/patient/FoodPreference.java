package seedu.address.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Patient's preferred food in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidFood(String)}
 */
public class FoodPreference {

    public static final String MESSAGE_CONSTRAINTS = "Food preferences can take any values, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String foodPreference;

    /**
     * Constructs an {@code FoodPreference}.
     *
     * @param food A valid food preference.
     */
    public FoodPreference(String food) {
        requireNonNull(food);
        checkArgument(isValidFood(food), MESSAGE_CONSTRAINTS);
        foodPreference = food;
    }

    /**
     * Returns true if a given string is a valid food details.
     */
    public static boolean isValidFood(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return foodPreference;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FoodPreference)) {
            return false;
        }

        FoodPreference otherFoodPreference = (FoodPreference) other;
        return foodPreference.equals(otherFoodPreference.foodPreference);
    }

    @Override
    public int hashCode() {
        return foodPreference.hashCode();
    }

}
