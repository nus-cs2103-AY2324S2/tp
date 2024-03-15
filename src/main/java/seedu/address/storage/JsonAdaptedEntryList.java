package seedu.address.storage;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Custom class to store Json EntryList
 */
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
