package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.RoomNumber;
import seedu.address.model.person.Telegram;

public class MessagesTest {

    private static final String FORMATTED_ALL_FIELDS_MESSAGE = "Johnny; Phone: 98765432; "
            + "Email: john@gmail.com; Room Number: 01-01; Telegram: John; Birthday: 01-01-2000";
    private static final String FORMATTED_ALL_MANDATORY_FIELDS_MESSAGE = "Johnny; Phone: 98765432";
    private static final String FORMATTED_ALL_MANDATORY_AND_SOME_OPTIONAL_FIELDS_MESSAGE = "Johnny; Phone: 98765432; "
            + "Room Number: 01-01; Birthday: 01-01-2000";

    @Test
    public void format_personWithAllFields_success() {
        Name name = new Name("Johnny");
        Phone phone = new Phone("98765432");
        Email email = new Email("john@gmail.com");
        RoomNumber roomNumber = new RoomNumber("01-01");
        Telegram telegram = new Telegram("John");
        Birthday birthday = new Birthday("01-01-2000");

        Person person = new Person(name, phone, email, roomNumber, telegram, birthday);
        assertMessageSuccess(person, FORMATTED_ALL_FIELDS_MESSAGE);
    }

    @Test
    public void format_personWithAllMandatoryFields_success() {
        Name name = new Name("Johnny");
        Phone phone = new Phone("98765432");
        Email email = null;
        RoomNumber roomNumber = null;
        Telegram telegram = null;
        Birthday birthday = null;

        Person person = new Person(name, phone, email, roomNumber, telegram, birthday);
        assertMessageSuccess(person, FORMATTED_ALL_MANDATORY_FIELDS_MESSAGE);
    }

    @Test
    public void format_personWithAllMandatoryAndSomeOptionalFields_success() {
        Name name = new Name("Johnny");
        Phone phone = new Phone("98765432");
        Email email = null;
        RoomNumber roomNumber = new RoomNumber("01-01");
        Telegram telegram = null;
        Birthday birthday = new Birthday("01-01-2000");

        Person person = new Person(name, phone, email, roomNumber, telegram, birthday);
        assertMessageSuccess(person, FORMATTED_ALL_MANDATORY_AND_SOME_OPTIONAL_FIELDS_MESSAGE);
    }

    /**
     * Executes the command and confirms that
     * - the formatted message is equal to {@code expectedMessage} <br>
     */
    private void assertMessageSuccess(Person inputPerson, String expectedMessage) {
        assertEquals(expectedMessage, Messages.format(inputPerson));
    }

}
