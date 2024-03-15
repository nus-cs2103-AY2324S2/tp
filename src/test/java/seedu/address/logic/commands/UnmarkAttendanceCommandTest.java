package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.Messages.MESSAGE_MISSING_NUSNET;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NusNet;
import seedu.address.model.person.Person;
import seedu.address.model.weeknumber.WeekNumber;


/**
 * Contains integration tests (interaction with the Model) for {@code MarkAttendanceCommand}.
 */
public class UnmarkAttendanceCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final NusNet testValidNusNet = new NusNet("e0000001");
    private final NusNet testMissingNusNet = new NusNet("e0000000");
    private final WeekNumber testValidWeekNo6 = new WeekNumber("6");
    private final WeekNumber testValidWeekNo1 = new WeekNumber("1");

    @Test
    public void equals() {
        UnmarkAttendanceCommand unmarkAttendanceCommand1 = new UnmarkAttendanceCommand(testValidNusNet,
                testValidWeekNo6);
        UnmarkAttendanceCommand unmarkAttendanceCommand2 =
                new UnmarkAttendanceCommand(ALICE.getNusNet(), testValidWeekNo6);
        UnmarkAttendanceCommand unmarkAttendanceCommand3 =
                new UnmarkAttendanceCommand(new NusNet("E0123457"), testValidWeekNo6);

        // same object -> passes
        assertEquals(unmarkAttendanceCommand1, unmarkAttendanceCommand2);

        // null -> fails
        assertNotEquals(null, unmarkAttendanceCommand2);

        // different person -> fails
        assertNotEquals(unmarkAttendanceCommand1, unmarkAttendanceCommand3);
    }

    @Test
    public void execute_validNusNetValidWeekNumber_unmarkSuccess() {
        HashSet<WeekNumber> aliceAttendanceUpdated = new HashSet<>(ALICE.getAttendance());

        aliceAttendanceUpdated.remove(testValidWeekNo6);

        Person aliceChanged = new Person(ALICE.getName(), ALICE.getPhone(), ALICE.getEmail(), ALICE.getNusNet(),
                ALICE.getAddress(), aliceAttendanceUpdated, ALICE.getTags());

        CommandResult expectedCommandResult =
                new CommandResult("Attendance is unmarked for student: Alice Pauline, e0000001, Week 6");

        UnmarkAttendanceCommand command = new UnmarkAttendanceCommand(testValidNusNet, testValidWeekNo6);

        Model expectedModel1 = expectedModel;

        expectedModel1.setPerson(ALICE, aliceChanged);

        assertCommandSuccess(command, model, expectedCommandResult, expectedModel1);
    }

    @Test
    public void execute_validNusNetDuplicateWeekNumber_unmarkSuccess() {
        UnmarkAttendanceCommand command = new UnmarkAttendanceCommand(testValidNusNet, testValidWeekNo1);

        Model expectedModel2 = expectedModel;

        HashSet<WeekNumber> aliceAttendanceUpdated = new HashSet<>(ALICE.getAttendance());

        aliceAttendanceUpdated.remove(testValidWeekNo1);

        Person aliceChanged = new Person(ALICE.getName(), ALICE.getPhone(), ALICE.getEmail(), ALICE.getNusNet(),
                ALICE.getAddress(), aliceAttendanceUpdated, ALICE.getTags());

        expectedModel2.setPerson(ALICE, aliceChanged);

        CommandResult expectedCommandResult =
                new CommandResult("Unmarked attendance for student: Alice Pauline, e0000001, Week 1");
        assertCommandSuccess(command, model, expectedCommandResult, expectedModel2);
    }

    @Test
    public void execute_missingNusNetValidWeekNumber_markFailure() {
        UnmarkAttendanceCommand command = new UnmarkAttendanceCommand(testMissingNusNet, testValidWeekNo1);
        assertCommandFailure(command, model, MESSAGE_MISSING_NUSNET);
    }
}
