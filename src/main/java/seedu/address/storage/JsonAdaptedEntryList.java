package seedu.address.storage;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class JsonAdaptedEntryList {
    private final List<JsonAdaptedEntry> entryList;

    @JsonCreator
    public JsonAdaptedEntryList(@JsonProperty("entryList") List<JsonAdaptedEntry> entryList) {
        this.entryList = entryList;
    }

    public List<JsonAdaptedEntry> getEntryList() {
        return entryList;
    }
}
