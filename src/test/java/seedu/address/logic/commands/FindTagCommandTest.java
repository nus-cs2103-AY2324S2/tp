package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;
import seedu.address.testutil.TypicalStudents;

public class FindTagCommandTest {
    private static final List<Student> OWES =
            Arrays.asList(TypicalStudents.BENSON);
    private static final List<Student> E =
            Arrays.asList(TypicalStudents.ALICE, TypicalStudents.BENSON, TypicalStudents.DANIEL);

    private Model model = new ModelManager(TypicalStudents.getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalStudents.getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_emptyString_allStudents() {
        String expectedMessage = String.format(FindTagCommand.MESSAGE_FOUND_PEOPLE, 7, "");
        FindTagCommand command = new FindTagCommand("");

        CommandTestUtil.assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(model.getFilteredStudentList(), TypicalStudents.getTypicalStudents());
    }

    @Test
    void execute_letterE_success() {
        String keyword = "E";
        FindTagCommand command = new FindTagCommand(keyword);
        String expectedMessage = String.format(FindTagCommand.MESSAGE_FOUND_PEOPLE, E.size(), keyword);
        expectedModel.updateFilteredStudentList(student -> E.contains(student));

        CommandTestUtil.assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(model.getFilteredStudentList(), E);
    }

    @Test
    void execute_owesMoney_success() {
        String keyword = "owesMoney";
        FindTagCommand command = new FindTagCommand(keyword);
        String expectedMessage = String.format(FindTagCommand.MESSAGE_FOUND_PEOPLE, OWES.size(), keyword);
        expectedModel.updateFilteredStudentList(student -> OWES.contains(student));

        CommandTestUtil.assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(model.getFilteredStudentList(), OWES);
    }

    @Test
    void equals() {
        FindTagCommand cmd = new FindTagCommand("tag");

        // same keyword -> returns true
        assertEquals(cmd, new FindTagCommand("tag"));

        // same object -> returns true
        assertEquals(cmd, cmd);

        // different keyword -> returns false
        assertNotEquals(cmd, new FindTagCommand("stag"));

        // different types -> returns false
        assertNotEquals(new FindTagCommand("tag"), new ListCommand());

        // null -> returns false
        assertNotEquals(new FindTagCommand("tag"), null);
    }

    @Test
    void testToString() {
        FindTagCommand cmd = new FindTagCommand("tag");
        assertEquals(cmd.toString(), FindTagCommand.class.getCanonicalName() + "{subString=tag}");
    }
}
