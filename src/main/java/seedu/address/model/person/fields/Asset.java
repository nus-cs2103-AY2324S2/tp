package seedu.address.model.person.fields;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Represents an asset in the address book.
 * Guarantees: immutable.
 */
public class Asset implements Field {

    /**
     * Stores all unique assets created.
     */
    private static final HashMap<String, Asset> assets = new HashMap<>();

    public static final Prefix PREFIX_ASSET = new Prefix("A/");
    private static final String MESSAGE_CONSTRAINTS = "Assets can take any values, and it should not be blank";

    /*
     * The first character of the asset must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    private static final String VALIDATION_REGEX = "[^\\s].*";

    private final String name;

    /**
     * Constructs an {@code Asset}.
     *
     * @param name A valid asset name.
     */
    public Asset(String name) {
        requireNonNull(name);
        checkArgument(isValid(name), MESSAGE_CONSTRAINTS);
        this.name = name;
    }

    /**
     * Returns true if a given string is a valid asset.
     */
    private static boolean isValid(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Parses a {@code String name} into an {@code Asset}.
     * Leading and trailing whitespaces will be trimmed.
     * <p>
     * Only creates a new {@code Asset} object if it does not already exist.
     * Otherwise, an existing {@code Asset} with the same name is returned.
     *
     * @throws IllegalArgumentException if the given {@code name} is invalid.
     */
    public static Asset of(String name) throws IllegalArgumentException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (assets.containsKey(trimmedName)) {
            return assets.get(trimmedName); // asset already exists
        }
        Asset newAsset = new Asset(trimmedName);
        assets.put(trimmedName, newAsset);
        return newAsset;
    }

    @Override
    @JsonValue
    public String toString() {
        return name;
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
        return name.equals(otherAsset.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
