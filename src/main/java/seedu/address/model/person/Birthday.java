package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Optional;

/**
 * Represents a Person's birthday in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidBirthday(String)}
 */
public class Birthday {
    public static final String BIRTHDAY_CONSTRAINTS =
            "Birthday should be in the format dd/mm/yyyy and should be before today";
    public static final String BIRTHDAY_FORMAT = "dd/MM/yyyy";

    public final LocalDate birthday;


    /**
     * Constructs a {@code Birthday}.
     *
     * @param birthday A valid birthday, or an empty string.
     */
    public Birthday(String birthday) {
        requireNonNull(birthday);
        if (birthday.isBlank()) {
            this.birthday = null;
            return;
        }
        checkArgument(isValidBirthday(birthday), BIRTHDAY_CONSTRAINTS);
        this.birthday = LocalDate.parse(birthday, DateTimeFormatter.ofPattern(BIRTHDAY_FORMAT));
    }


    /**
     * Returns true if a given string is a valid birthday.
     */
    public static boolean isValidBirthday(String test) {
        if (test == null || test.isBlank()) {
            return true;
        }
        test = test.strip();
        try {
            LocalDate date = LocalDate.parse(test, DateTimeFormatter.ofPattern(BIRTHDAY_FORMAT));
            return date.format(DateTimeFormatter.ofPattern(BIRTHDAY_FORMAT)).equals(test)
                    && date.isBefore(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return Optional.ofNullable(birthday).map(
                birthdayObj -> birthdayObj.format(DateTimeFormatter.ofPattern(
                        BIRTHDAY_FORMAT
                ))).orElse("");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Birthday)) {
            return false;
        }
        Birthday otherBirthday = (Birthday) o;
        return Objects.equals(birthday, otherBirthday.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(birthday);
    }
}
