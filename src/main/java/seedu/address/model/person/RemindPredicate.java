package seedu.address.model.person;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMISSION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMPLOYMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.Prefix;

/**
 * Tests that a {@code Person}'s Note deadline is today or after today.
 */
public class RemindPredicate implements Predicate<Person> {

    @Override
    public boolean test(Person person){
        Note currNote = person.getNote();
        if (!currNote.toString().contains(" by: ")) {
            return false;
        }
        String[] noteAndDate = currNote.toString().split(" by: ");


        try {

            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");

            LocalDate convertedDate = LocalDate.parse(noteAndDate[1], inputFormat);

            String outputDate = convertedDate.toString();

            LocalDate noteDate = LocalDate.parse(outputDate);

            LocalDate currDate = LocalDate.now();
            if (!noteDate.isBefore(currDate)) {
                return true;
            } else {
                return false;
            }
        } catch (DateTimeException e) {
            // don't change the date in this case
            // as it is not a valid date, return false.
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof RemindPredicate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).toString();
    }
}
