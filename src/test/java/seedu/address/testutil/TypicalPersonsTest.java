package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalPersons;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

class TypicalPersonsTest {
    @Test
    void execute_getTypicalAddressBook_success() {
        AddressBook ab = getTypicalAddressBook();
    }

    @Test
    void execute_getTypicalPersons_success() {
        List<Person> list = getTypicalPersons();
    }
}
