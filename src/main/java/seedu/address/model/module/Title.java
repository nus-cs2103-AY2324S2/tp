package seedu.address.model.module;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Represents a Module's title in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTitle(String)}
 */
public class Title {
    private final String title;

    @JsonCreator
    public Title(String title) {
        this.title = title;
    }

    //    TODO(yadunut): Validate title
    public static boolean isValidTitle(String test) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public String getTitle() {
        return title;
    }
}
