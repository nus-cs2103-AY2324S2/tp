package seedu.address.storage;

import seedu.address.model.person.Person;
import seedu.address.model.person.Supplier;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedSupplier extends JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";


    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedSupplier(Person source) {
        super(source);
        Supplier supplier = (Supplier) source;
        setProduct(supplier.getProduct().value);
        setPrice(supplier.getPrice().value);
    }
}
