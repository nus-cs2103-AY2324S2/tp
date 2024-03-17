package seedu.address.model.asset;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Represents an asset in the address book.
 * Guarantees: immutable.
 */
public class Asset {

    /**
     * Stores all unique assets created.
     */
    private static final HashMap<String, Asset> assetsHashMap = new HashMap<>();
    private static final String MESSAGE_CONSTRAINTS = "Asset names should be alphanumeric";
    private static final String VALIDATION_REGEX = "\\p{Alnum}+";

    private final String assetName;

    private Asset(String assetName) {
        this.assetName = assetName;
    }

    /**
     * Parses a {@code String assetName} into an {@code Asset}.
     * Leading and trailing whitespaces will be trimmed.
     * <p>
     * Only creates a new {@code Asset} object if it does not already exist.
     * Otherwise, an existing {@code Asset} with the same name is returned.
     *
     * @throws IllegalArgumentException if the given {@code assetName} is invalid.
     */
    public static Asset of(String assetName) throws IllegalArgumentException {
        requireNonNull(assetName);
        String trimmedName = assetName.trim();
        checkArgument(isValid(trimmedName), MESSAGE_CONSTRAINTS);
        if (assetsHashMap.containsKey(trimmedName)) {
            return assetsHashMap.get(trimmedName);
        }
        Asset newAsset = new Asset(trimmedName);
        assetsHashMap.put(trimmedName, newAsset);
        return newAsset;
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

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Asset)) {
            return false;
        }

        Asset otherTag = (Asset) other;
        return assetName.equals(otherTag.assetName);
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
