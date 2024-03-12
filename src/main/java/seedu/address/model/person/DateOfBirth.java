package seedu.address.model.person;

import seedu.address.commons.core.date.Date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import static seedu.address.commons.util.AppUtil.checkArgument;

public class DateOfBirth extends Date {
    public final LocalDate dob;

    public DateOfBirth(String dateOfBirth) {
        super(dateOfBirth);
        checkArgument(isValidDateOfBirth(dateOfBirth), MESSAGE_CONSTRAINTS);
        this.dob = LocalDate.parse(dateOfBirth);
    }

    public static boolean isValidDateOfBirth(String dateOfBirth) {
        LocalDate dob = LocalDate.parse(dateOfBirth);
        return dob.isBefore(LocalDate.now());
    }

    @Override
    public String toString() {
        DateTimeFormatter dateformatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        return dateformatter.format(this.dob);
    }
}
