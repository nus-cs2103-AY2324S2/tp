package seedu.address.model.person.attribute;

import java.time.LocalDate;

/**
 * Represents an attribute that holds a date value in a person's record.
 * This class extends {@link Attribute} to include functionality specific to date management.
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
