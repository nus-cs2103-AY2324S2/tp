package seedu.address.model.person.attribute;

import java.time.LocalDate;

/**
 * Date attribute with LocalDate value
 */
public class BirthdayAttribute extends DateAttribute {
    /**
     * Constructs a BirthdayAttribute.
     *
     * @param name Name of the attribute.
     * @param date A LocalDate.
     */
    public BirthdayAttribute(String name, LocalDate date) {
        super(name, date);
        // Validate that the provided date is before today
        if (!date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Birthday must be before today.");
        }
    }
}
