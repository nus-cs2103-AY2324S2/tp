package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.testutil.PersonBuilder;

public class HousekeeperTest {
    private static Housekeeper copyBob;

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    static {
        try {
            ArrayList<String> bobTags = new ArrayList<>();
            bobTags.add(VALID_TAG_HUSBAND);
            bobTags.add(VALID_TAG_FRIEND);
            copyBob = new Housekeeper(
                    ParserUtil.parseName(VALID_NAME_BOB),
                    ParserUtil.parsePhone(VALID_PHONE_BOB),
                    ParserUtil.parseEmail(VALID_EMAIL_BOB),
                    ParserUtil.parseAddress(VALID_ADDRESS_BOB),
                    ParserUtil.parseTags(bobTags),
                    ParserUtil.parseType(VALID_TYPE_BOB));

            //Assert that created Housekeeper object is not null
            assertNotNull(copyBob);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void equals() {
        // same values -> returns true
        assertTrue(copyBob.equals(BOB));

        // same object -> returns true
        assertTrue(copyBob.equals(copyBob));

        // null -> returns false
        assertFalse(copyBob.equals(null));

        // different type -> returns false
        assertFalse(copyBob.equals(5));

        // different person -> returns false
        assertFalse(copyBob.equals(AMY));

        // different name -> returns false
        Person editedCopyBob = new PersonBuilder(BOB).withName(VALID_NAME_AMY).build();
        assertFalse(copyBob.equals(editedCopyBob));

        // different phone -> returns false
        editedCopyBob = new PersonBuilder(BOB).withPhone(VALID_PHONE_AMY).build();
        assertFalse(copyBob.equals(editedCopyBob));

        // different email -> returns false
        editedCopyBob = new PersonBuilder(BOB).withEmail(VALID_EMAIL_AMY).build();
        assertFalse(copyBob.equals(editedCopyBob));

        // different address -> returns false
        editedCopyBob = new PersonBuilder(BOB).withAddress(VALID_ADDRESS_AMY).build();
        assertFalse(copyBob.equals(editedCopyBob));

        // different tags -> returns false
        editedCopyBob = new PersonBuilder(BOB).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(copyBob.equals(editedCopyBob));

        // different type -> returns false
        editedCopyBob = new PersonBuilder(BOB).withType(VALID_TYPE_AMY).build();
        assertFalse(copyBob.equals(editedCopyBob));
    }

    @Test
    public void isClient() {
        //is a client -> return true
        assertTrue(AMY.isClient());

        //is a housekeeper -> return false
        assertFalse(BOB.isClient());

        //is a housekeeper -> return false
        assertFalse(copyBob.isClient());
    }
}
