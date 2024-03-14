package seedu.address.model.person.attribute;

import java.time.LocalDate;

/**
 * Date attribute with LocalDate value
 */
public class DateAttribute extends Attribute {
    private LocalDate value;
    /**
     * Constructs a DateAttribute.
     *
     * @param name Name of the attribute.
     * @param value A LocalDate.
     */
    public DateAttribute(String name, LocalDate value) {
        super(name);
        this.value = value;
    }

    public LocalDate getValue() {
        return value;
    }

    @Override
    public String getValueAsString() {
        return value.toString();
    }

    // Function for searching records by a specific date
    public boolean isOnDate(LocalDate date) {
        return value.isEqual(date);
    }
}
