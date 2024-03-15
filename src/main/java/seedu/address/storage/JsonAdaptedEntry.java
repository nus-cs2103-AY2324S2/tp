package seedu.address.storage;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class allowing entries to be stored as a Json
 */
public class JsonAdaptedEntry {
    private final String category;
    private final String description;

    /**
     * Creates an entry to be stored in Json
     * @param category
     * @param description
     */
    @JsonCreator
    public JsonAdaptedEntry(@JsonProperty("category") String category,
                            @JsonProperty("description") String description) {
        this.category = category;
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }
}
