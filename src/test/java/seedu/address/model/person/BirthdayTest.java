package seedu.address.model.person;

import static seedu.address.model.person.Birthday.BIRTHDAY_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class BirthdayTest {

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "asdf";
        assertThrows(IllegalArgumentException.class, () -> new Birthday(invalidDate));
    }

    @Test
    public void constructor_dateAfterPresent_throwsIllegalArgumentException() {
        String futureDate = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern(BIRTHDAY_FORMAT));
        assertThrows(IllegalArgumentException.class, () -> new Birthday(futureDate));
    }
}
