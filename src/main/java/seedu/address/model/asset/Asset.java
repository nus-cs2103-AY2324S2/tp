package seedu.address.model.asset;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Represents an asset in the address book.
 * Guarantees: immutable.
 */
public class Asset {

    public static final String MESSAGE_CONSTRAINTS = "Asset names can take any values, and it should not be blank";
    private static final String VALIDATION_REGEX = "\\S.*";

    private final String assetName;

    /**
     * Constructs a {@code Asset}.
     *
     * @param assetName A valid asset name.
     */
    public Asset(String assetName) {
        requireNonNull(assetName);
        assetName = assetName.trim();
        checkArgument(isValid(assetName), MESSAGE_CONSTRAINTS);
        this.assetName = assetName;
    }

    @JsonValue
    public String get() {
        return assetName;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    private static boolean isValid(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws IllegalArgumentException if the given {@code name} is invalid.
     */
    public static Asset of(String assetName) throws IllegalArgumentException {
        requireNonNull(assetName);
        String trimmedName = assetName.trim();
        return new Asset(trimmedName);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Asset)) {
            return false;
        }

        Asset otherAsset = (Asset) other;
        return assetName.equals(otherAsset.assetName);
    }

    @Override
    public int hashCode() {
        return assetName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + assetName + ']';
    }

}
