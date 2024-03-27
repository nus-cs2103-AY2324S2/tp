package seedu.address.model.alias;

import java.util.HashMap;
import java.util.Map;

public class Alias {
    private Map<String, String> mappings;

    public Alias() {
        this.mappings = new HashMap<>();
    }

    public Alias(HashMap map) {
        this.mappings = map;
    }

    public String getAlias(String string) {
        if (this.mappings.get(string) == null ) {
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
