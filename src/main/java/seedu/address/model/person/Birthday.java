package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Optional;

/**
 * Represents a Person's telegram handle in the logbook.
 * Guarantees: immutable; is valid as declared in {@link #isValidBirthday(String)}
 */
public class Birthday {
    public static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("dd LLLL yyyy");
    private static final String[] VALID_FORMATS = {"dd/MM/yyyy", "dd-MM-yyyy", "yyyy-MM-dd", "yyyy/MM/dd"};
    private static final String[] VALID_FORMATS_REGEX = {"\\d{2}/\\d{2}/\\d{4}", "\\d{2}-\\d{2}-\\d{4}",
        "\\d{4}-\\d{2}-\\d{2}", "\\d{4}/\\d{2}/\\d{2}"};
    public static final String MESSAGE_CONSTRAINTS =
            "Birthday should not be blank and has to be in one of the given format: ["
            + String.join("] [", VALID_FORMATS) + ']';


    public final LocalDate value;

    /**
     * Constructs an {@code Birthday}.
     *
     * @param birthday A valid birthday.
     */
    public Birthday(String birthday) {
        requireNonNull(birthday);
        checkArgument(isValidBirthday(birthday.trim()), MESSAGE_CONSTRAINTS);
        value = parseDate(birthday.trim()).orElse(null);
        assert this.value != null;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidBirthday(String test) {
        try {
            return parseDate(test).isPresent();
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private static Optional<LocalDate> parseDate(String date) {
        for (int i = 0; i < VALID_FORMATS.length; ++i) {
            if (date.matches(VALID_FORMATS_REGEX[i])) {
                DateTimeFormatter validationFormatter = DateTimeFormatter.ofPattern(VALID_FORMATS[i], Locale.ENGLISH);
                LocalDate parsed = LocalDate.parse(date, validationFormatter);
                return Optional.of(parsed)
                        .filter(d -> d.format(validationFormatter).equals(date) && !d.isAfter(LocalDate.now()));
            }
        }
        return Optional.empty();
    }

    @Override
    public String toString() {
        return value.format(OUTPUT_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Birthday)) {
            return false;
        }

        Birthday otherBirthday = (Birthday) other;
        return value.equals(otherBirthday.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
