package seedu.realodex.logic.parser;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A prefix that marks the beginning of an argument in an arguments string.
 * E.g. 't/' in 'add James t/ friend'.
 */
public class Prefix {
    private final String prefix;
    private final String prefixDescription;

    /**
     * Constructs a Prefix object with the given prefix and its representation.
     *
     * @param prefix The prefix string.
     * @param prefixDescription The string representation of the prefix.
     */
    public Prefix(String prefix, String prefixDescription) {
        this.prefix = prefix;
        this.prefixDescription = prefixDescription.toUpperCase();
    }

    /**
     * Retrieves the prefix string.
     *
     * @return The prefix string.
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Generates a message listing the missing prefixes from the provided argument multimap,
     * based on the list of compulsory prefixes.
     *
     * @param argMultimap The argument multimap containing prefixes mapped to their respective values.
     * @param listOfCompulsoryPrefix An array of Prefix objects representing compulsory prefixes.
     * @return A string message listing the missing prefixes separated by commas.
     */
    public static String returnMessageOfMissingPrefixes(Map<Prefix, List<String>> argMultimap,
                                                        Prefix[] listOfCompulsoryPrefix) {
        List<String> missingPrefixes = Stream.of(listOfCompulsoryPrefix)
                .filter(prefix -> !argMultimap.containsKey(prefix))
                .map(Prefix::toStringWithRepresentation)
                .collect(Collectors.toList());

        return String.join(", ", missingPrefixes);
    }

    /**
     * Returns the string representation of the Prefix object.
     *
     * @return The prefix string.
     */
    @Override
    public String toString() {
        return getPrefix();
    }

    /**
     * Returns the string representation of the Prefix object with its representation.
     *
     * @return The prefix string with its representation.
     */
    public String toStringWithRepresentation() {
        return getPrefix() + prefixDescription;
    }

    /**
     * Computes the hash code for the Prefix object.
     *
     * @return The hash code value for the Prefix object.
     */
    @Override
    public int hashCode() {
        return prefix == null ? 0 : prefix.hashCode();
    }

    /**
     * Compares the Prefix object with another object for equality.
     *
     * @param other The object to compare with.
     * @return {@code true} if the objects are equal; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Prefix)) {
            return false;
        }

        Prefix otherPrefix = (Prefix) other;
        return prefix.equals(otherPrefix.prefix);
    }
}
