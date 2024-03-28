package seedu.address.storage;

import seedu.address.model.person.Maintainer;
import seedu.address.model.person.Person;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedMaintainer extends JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";


    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedMaintainer(Person source) {
        super(source);
        Maintainer supplier = (Maintainer) source;
        setSkill(supplier.getSkill().value);
        setCommission(supplier.getCommission().value);
    }
}
