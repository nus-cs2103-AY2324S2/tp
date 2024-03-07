package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.NusNet;
import seedu.address.model.weeknumber.WeekNumber;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.*;
import static seedu.address.testutil.TypicalPersons.FIONA;

/**
 * Contains integration tests (interaction with the Model) for {@code MarkAttendanceCommand}.
 */
public class MarkAttendanceCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NusNet testNusNet = new NusNet("e0123456");
        WeekNumber testWeekNo = new WeekNumber("6");

        MarkAttendanceCommand markAttendanceCommand1 = new MarkAttendanceCommand(testNusNet, testWeekNo);
        MarkAttendanceCommand markAttendanceCommand2 =
                new MarkAttendanceCommand(new NusNet("E0123456"), new WeekNumber("6"));
        MarkAttendanceCommand markAttendanceCommand3 =
                new MarkAttendanceCommand(new NusNet("E0123457"), new WeekNumber("6"));

        // same object -> passes
        assertEquals(markAttendanceCommand1, markAttendanceCommand2);

        // null -> fails
        assertNotEquals(null, markAttendanceCommand2);

        // different person -> fails
        assertNotEquals(markAttendanceCommand1, markAttendanceCommand3);
    }
//
//    @Test
//    public void execute_zeroKeywords_noPersonFound() {
//        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
//        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
//        FindPersonCommand command = new FindPersonCommand(predicate);
//        expectedModel.updateFilteredPersonList(predicate);
//        assertCommandSuccess(command, model, expectedMessage, expectedModel);
//        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
//    }
////
////    @Test
////    public void execute_multipleKeywords_multiplePersonsFound() {
////        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
////        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
////        FindPersonCommand command = new FindPersonCommand(predicate);
////        expectedModel.updateFilteredPersonList(predicate);
////        assertCommandSuccess(command, model, expectedMessage, expectedModel);
////        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
////    }
////
////    @Test
////    public void toStringMethod() {
////        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Arrays.asList("keyword"));
////        FindPersonCommand findCommand = new FindPersonCommand(predicate);
////        String expected = FindPersonCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
////        assertEquals(expected, findCommand.toString());
////    }
////
////    /**
////     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
////     */
////    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
////        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
////    }
}
