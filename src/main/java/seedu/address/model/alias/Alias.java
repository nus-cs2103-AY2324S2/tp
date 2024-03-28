package seedu.address.model.alias;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a collection of alias mappings.
 * Each alias maps to a corresponding term to be replaced.
 */
public class Alias {
    private Map<String, String> mappings;

    /**
     * Constructs an empty Alias instance.
     * Initializes the mappings to an empty HashMap.
     */
    public Alias() {
        this.mappings = new HashMap<>();
    }


    /**
     * Constructs an Alias instance with the specified mappings.
     *
     * @param map The mappings to initialize the Alias with.
     */
    public Alias(HashMap map) {
        this.mappings = map;
    }

    /**
     * Retrieves the term corresponding to the given alias.
     *
     * @param string The alias to retrieve the term for.
     * @return The term corresponding to the alias, or null if the alias doesn't exist.
     */
    public String getAlias(String string) {
        if (!this.mappings.containsKey(string)) {
            return null;
        }
        return this.mappings.get(string);
    }

    public void addAlias(String alias, String toReplace) {
        this.mappings.put(alias, toReplace);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Alias)) {
            return false;
        }
        Alias otherAlias = (Alias) other;
        return this.mappings.equals(otherAlias.mappings);
    }

}
