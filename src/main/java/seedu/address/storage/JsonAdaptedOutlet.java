package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.article.Outlet;

/**
 * Jackson-friendly version of {@link Outlet}.
 */
class JsonAdaptedOutlet {

    private final String outletName;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedOutlet(String outletName) {
        this.outletName = outletName;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedOutlet(Outlet source) {
        outletName = source.outletName;
    }

    @JsonValue
    public String getOutletName() {
        return outletName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Outlet} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted outlet.
     */
    public Outlet toModelType() throws IllegalValueException {
        if (!Outlet.isValidOutletName(outletName)) {
            throw new IllegalValueException(Outlet.MESSAGE_CONSTRAINTS);
        }
        return new Outlet(outletName);
    }

}
