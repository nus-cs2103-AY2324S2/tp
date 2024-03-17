package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Tag;

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

  /*  public Tag toModelType() throws IllegalValueException {
        if (!Article.isValidName(name)) {
            throw new IllegalValueException(Article.MESSAGE_CONSTRAINTS);
        }
        return new Article(name); */




}
