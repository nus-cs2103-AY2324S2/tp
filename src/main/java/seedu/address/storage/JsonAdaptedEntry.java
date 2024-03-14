package seedu.address.storage;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonAdaptedEntry {
    private final String category;
    private final String description;

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
