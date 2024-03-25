package seedu.realodex.logic.parser;

import java.util.ArrayList;
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

    public Prefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }



    public static String returnMessageOfMissingPrefixes(Map<Prefix, List<String>> argMultimap,
                                                        Prefix[] listOfCompulsoryPrefix) {
        List<String> missingPrefixes = Stream.of(listOfCompulsoryPrefix)
                .filter(prefix -> !argMultimap.containsKey(prefix))
                .map(Prefix::toString)
                .collect(Collectors.toList());

        return "Prefixes That Are Missed Are : " + String.join(", ", missingPrefixes);
    }


    @Override
    public String toString() {
        return getPrefix();
    }

    @Override
    public int hashCode() {
        return prefix == null ? 0 : prefix.hashCode();
    }

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
