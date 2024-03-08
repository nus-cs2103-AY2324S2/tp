package seedu.address.storage;

import seedu.address.model.person.Maintainer;
import seedu.address.model.person.Person;
import seedu.address.model.person.Supplier;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedMaintainer extends JsonAdaptedPerson{

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";
    private final String skill;
    private final String commission;


    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedMaintainer(Person source) {
        super(source);
        Maintainer supplier = (Maintainer) source;
        skill = supplier.getSkill().skill;
        commission = supplier.getCommission().commission;
    }
}
