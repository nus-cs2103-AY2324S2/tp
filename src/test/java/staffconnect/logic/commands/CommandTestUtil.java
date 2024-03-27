package staffconnect.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static staffconnect.logic.parser.CliSyntax.PREFIX_AVAILABILITY;
import static staffconnect.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static staffconnect.logic.parser.CliSyntax.PREFIX_EMAIL;
import static staffconnect.logic.parser.CliSyntax.PREFIX_FACULTY;
import static staffconnect.logic.parser.CliSyntax.PREFIX_MODULE;
import static staffconnect.logic.parser.CliSyntax.PREFIX_NAME;
import static staffconnect.logic.parser.CliSyntax.PREFIX_PHONE;
import static staffconnect.logic.parser.CliSyntax.PREFIX_STARTDATE;
import static staffconnect.logic.parser.CliSyntax.PREFIX_TAG;
import static staffconnect.logic.parser.CliSyntax.PREFIX_VENUE;
import static staffconnect.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import staffconnect.commons.core.index.Index;
import staffconnect.logic.commands.exceptions.CommandException;
import staffconnect.model.Model;
import staffconnect.model.StaffBook;
import staffconnect.model.meeting.Description;
import staffconnect.model.meeting.MeetDateTime;
import staffconnect.model.meeting.Meeting;
import staffconnect.model.person.Person;
import staffconnect.model.person.predicates.NameContainsKeywordsPredicate;
import staffconnect.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_MODULE_AMY = "CS2103";
    public static final String VALID_MODULE_BOB = "CS2103T";
    public static final String VALID_FACULTY_AMY = "Computing";
    public static final String VALID_FACULTY_BOB = "Science";
    public static final String VALID_VENUE_AMY = "Block 312, Amy Street 1";
    public static final String VALID_VENUE_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_AVAILABILITY_MON = "mon 12:00 14:00";
    public static final String VALID_AVAILABILITY_THUR = "thurs 12:00 14:00";



    public static final String VALID_DESCRIPTION_MIDTERMS = "Meet for midterms";
    public static final String VALID_DESCRIPTION_FINALS = "Meet for finals";
    public static final String VALID_DATE_MARCH = "12/03/2023 18:00";
    public static final String VALID_DATE_APRIL = "15/04/2024 15:00";


    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String MODULE_DESC_AMY = " " + PREFIX_MODULE + VALID_MODULE_AMY;
    public static final String MODULE_DESC_BOB = " " + PREFIX_MODULE + VALID_MODULE_BOB;
    public static final String FACULTY_DESC_AMY = " " + PREFIX_FACULTY + VALID_FACULTY_AMY;
    public static final String FACULTY_DESC_BOB = " " + PREFIX_FACULTY + VALID_FACULTY_BOB;
    public static final String VENUE_DESC_AMY = " " + PREFIX_VENUE + VALID_VENUE_AMY;
    public static final String VENUE_DESC_BOB = " " + PREFIX_VENUE + VALID_VENUE_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String AVAILABILITY_DESC_MON = " " + PREFIX_AVAILABILITY + VALID_AVAILABILITY_MON;
    public static final String AVAILABILITY_DESC_THUR = " " + PREFIX_AVAILABILITY + VALID_AVAILABILITY_THUR;

    public static final String DESCRIPTION_MIDTERM = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_MIDTERMS;

    public static final String DATE_STARTDATE = " " + PREFIX_STARTDATE + VALID_DATE_MARCH;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_MODULE_DESC = " " + PREFIX_MODULE; // empty string not allowed for modules
    public static final String INVALID_FACULTY_DESC = " " + PREFIX_FACULTY + "faculty"; // must match a NUS faculty
    public static final String INVALID_VENUE_DESC = " " + PREFIX_VENUE; // empty string not allowed for venues
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_AVAILABILITY_DESC =
            " " + PREFIX_AVAILABILITY + "mon*"; // '*' not allowed in availabilities

    public static final String INVALID_DESCRIPTION = " " + PREFIX_DESCRIPTION + "&@#&*@*&@*"; //special characters

    public static final String INVALID_STARTDATE = " " + PREFIX_STARTDATE + "12-04-2023 1800";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;
    public static final Meeting VALID_MEETING =
        new Meeting(new Description(VALID_DESCRIPTION_MIDTERMS), new MeetDateTime(VALID_DATE_MARCH));
    public static final Meeting VALID_MEETING_FINALS =
        new Meeting(new Description(VALID_DESCRIPTION_FINALS), new MeetDateTime(VALID_DATE_MARCH));
    public static final Meeting VALID_MEETING_APRIL =
        new Meeting(new Description(VALID_DESCRIPTION_MIDTERMS), new MeetDateTime(VALID_DATE_APRIL));

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withModule(VALID_MODULE_AMY)
                .withFaculty(VALID_FACULTY_AMY).withVenue(VALID_VENUE_AMY)
                .withTags(VALID_TAG_FRIEND).withAvailabilities(VALID_AVAILABILITY_MON).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withModule(VALID_MODULE_BOB)
                .withFaculty(VALID_FACULTY_BOB).withVenue(VALID_VENUE_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
                .withAvailabilities(VALID_AVAILABILITY_MON, VALID_AVAILABILITY_THUR).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the staff book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        StaffBook expectedStaffBook = new StaffBook(actualModel.getStaffBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedStaffBook, actualModel.getStaffBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s staff book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
