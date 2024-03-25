package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.date.Date.isValidDate;
import static seedu.address.logic.Messages.MESSAGE_APPOINTMENTS_LISTED_OVERVIEW;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.model.appointment.Time.isValidTime;
import static seedu.address.model.person.Nric.isValidNric;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.ALICE_APPT;
import static seedu.address.testutil.TypicalAppointments.ALICE_APPT_1;
import static seedu.address.testutil.TypicalAppointments.AMY_APPT;
import static seedu.address.testutil.TypicalAppointments.getTypicalAddressBookWithAppointments;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.date.Date;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.AppointmentContainsKeywordsPredicate;
import seedu.address.model.appointment.Time;
import seedu.address.model.person.Nric;

/**
 * Contains integration tests (interaction with the Model) for {@code FindPersonCommand}.
 */
public class FindAppCommandTest {
    private Model model = new ModelManager(getTypicalAddressBookWithAppointments(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBookWithAppointments(), new UserPrefs());

    @Test
    public void equals() {
        AppointmentContainsKeywordsPredicate firstPredicate =
                new AppointmentContainsKeywordsPredicate(
                        Optional.of(new Nric("S1234567A")),
                        Optional.of(new Date("2024-12-31")),
                        Optional.of(new Time("10:00"))
                );
        AppointmentContainsKeywordsPredicate secondPredicate =
                new AppointmentContainsKeywordsPredicate(
                        Optional.of(new Nric("T0123456A")),
                        Optional.of(new Date("2024-12-30")),
                        Optional.of(new Time("09:00"))
                );

        FindAppCommand findFirstCommand = new FindAppCommand(firstPredicate);
        FindAppCommand findSecondCommand = new FindAppCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindAppCommand findFirstCommandCopy = new FindAppCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_invalidCommandFormat() {
        assertThrows(ParseException.class, () -> preparePredicate(""));
    }

    @Test
    public void execute_multipleKeywords_oneAppointmentFound() throws ParseException {
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 1);
        AppointmentContainsKeywordsPredicate predicate = preparePredicate(
                " i/T0123456A d/2024-03-16 from/11:00"
        );
        FindAppCommand command = new FindAppCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(AMY_APPT), model.getFilteredAppointmentList());
    }

    @Test
    public void execute_sameNric_twoAppointmentsFound() throws ParseException {
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 3);
        AppointmentContainsKeywordsPredicate predicate = preparePredicate(
                " i/T0123456A"
        );
        FindAppCommand command = new FindAppCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE_APPT, ALICE_APPT_1, AMY_APPT), model.getFilteredAppointmentList());
    }

    @Test
    public void execute_sameNricAndTime_twoAppointmentsFound() throws ParseException {
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 2);
        AppointmentContainsKeywordsPredicate predicate = preparePredicate(
                " i/T0123456A from/16:00"
        );
        FindAppCommand command = new FindAppCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE_APPT, ALICE_APPT_1), model.getFilteredAppointmentList());
    }

    @Test
    public void toStringMethod() {
        AppointmentContainsKeywordsPredicate predicate = new AppointmentContainsKeywordsPredicate(
                Optional.of(new Nric("S1234567A")),
                Optional.of(new Date("2024-12-31")),
                Optional.of(new Time("10:00"))
        );
        FindAppCommand findAppCommand = new FindAppCommand(predicate);
        String expected = FindAppCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findAppCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code AppointmentContainsKeywordsPredicate}.
     */
    private AppointmentContainsKeywordsPredicate preparePredicate(String userInput) throws ParseException {
        String trimmedArgs = userInput.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAppCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_NAME, PREFIX_NRIC,
                        PREFIX_DATE, PREFIX_START_TIME, PREFIX_END_TIME);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_NRIC,
                PREFIX_DATE, PREFIX_START_TIME, PREFIX_END_TIME);

        Optional<Nric> nricFilter = Optional.empty();
        Optional<Date> dateFilter = Optional.empty();
        Optional<Time> timeFilter = Optional.empty();

        if (argMultimap.getValue(PREFIX_NRIC).isPresent()) {
            if (isValidNric(argMultimap.getValue(PREFIX_NRIC).orElse(""))) {
                nricFilter = Optional.of(new Nric(argMultimap.getValue(PREFIX_NRIC).orElse("")));
            } else {
                throw new ParseException(Nric.MESSAGE_CONSTRAINTS);
            }
        }

        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            if (isValidDate(argMultimap.getValue(PREFIX_DATE).orElse(""))) {
                dateFilter = Optional.of(new Date(argMultimap.getValue(PREFIX_DATE).orElse("")));
            } else {
                throw new ParseException(Date.MESSAGE_CONSTRAINTS);
            }
        }

        if (argMultimap.getValue(PREFIX_START_TIME).isPresent()) {
            if (isValidTime(argMultimap.getValue(PREFIX_START_TIME).orElse(""))) {
                timeFilter = Optional.of(new Time(argMultimap.getValue(PREFIX_START_TIME).orElse("")));
            } else {
                throw new ParseException(Time.MESSAGE_CONSTRAINTS);
            }
        }

        AppointmentContainsKeywordsPredicate predicate = new AppointmentContainsKeywordsPredicate(
                nricFilter, dateFilter, timeFilter);
        return predicate;
    }
}
