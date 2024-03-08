package seedu.address.storage;

import seedu.address.model.person.Person;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Supplier;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedSupplier extends JsonAdaptedPerson{

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";
    private final String product;
    private final String price;


    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedSupplier(Person source) {
        super(source);
        Supplier supplier = (Supplier) source;
        product = supplier.getProduct().product;
        price = supplier.getPrice().price;
    }
}
