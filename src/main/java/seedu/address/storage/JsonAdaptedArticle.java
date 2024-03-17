package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
public class JsonAdaptedArticle {
    private final String name;

    /**
     *  Construct a {@code JsonAdaptedArticle} with the given {@code name}.
     * @param name
     */
    @JsonCreator
    public JsonAdaptedArticle(String name) {
        this.name = name;
    }

    @JsonValue
    public String getArticleName() {
        return name;
    }




}
