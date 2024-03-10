package seedu.address.model.person;

import java.time.LocalDate;

class BirthdayAttribute extends DateAttribute {
    public BirthdayAttribute(String name, LocalDate date) {
        super(name, date);
        // Validate that the provided date is before today
        if (!date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Birthday must be before today.");
        }
    }
}
